package com.epam.java.se;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class JavaKeyWordsAnalyzer {
    private FileInputStream inputStream;
    private Set<String> keywords;
    private Map<String, Integer> analyzedMap = new HashMap<>();

    public void makeOutputAnalyzedFile(String fileToAnalyzeName) throws IOException {
        Objects.requireNonNull(fileToAnalyzeName);

        openFileToAnalyze(fileToAnalyzeName);

        analyzeFile();
        makeOutput();
    }

    private void openFileToAnalyze(String fileToAnalyzeName) throws FileNotFoundException {
        this.inputStream = new FileInputStream(fileToAnalyzeName);
    }

    private void analyzeFile() throws IOException {
        prepareListWithKeywords();
        analyze();
    }

    private void analyze() throws IOException {
        final StringBuilder javaFileTextBuilder = new StringBuilder();
        int readSymbol;
        List<String> fileLinesList = new ArrayList<>();

        while ((readSymbol = inputStream.read()) != -1) {

            javaFileTextBuilder.append((char) readSymbol);
        }

        StringTokenizer tokenizer =
                new StringTokenizer(javaFileTextBuilder.toString(), System.getProperty("line.separator"));

        while (tokenizer.hasMoreTokens()) {
            fileLinesList.add(tokenizer.nextToken());
        }

        fileLinesList.forEach((line) -> keywords.forEach((word) -> {
            if (line.contains(word)) addValueToMap(word);
        }));

    }

    private void addValueToMap(String word) {
        if (analyzedMap.containsKey(word)) {
            analyzedMap.put(word, analyzedMap.get(word) + 1);
        } else {
            analyzedMap.put(word, 1);
        }
    }

    private void prepareListWithKeywords() throws IOException {
        final StringBuilder keywordsBuilder = new StringBuilder();
        int readSymbol;
        Set<String> keywordList = new HashSet<>();
        try (FileInputStream keywordsInput = new FileInputStream("keywords.txt")) {
            while ((readSymbol = keywordsInput.read()) != -1) {
                keywordsBuilder.append((char) readSymbol);
            }

            StringTokenizer tokenizer =
                    new StringTokenizer(keywordsBuilder.toString(), System.getProperty("line.separator"));

            while (tokenizer.hasMoreTokens()) {
                keywordList.add(tokenizer.nextToken());
            }
        }

        this.keywords = keywordList;
    }

    private void makeOutput() {

    }
}

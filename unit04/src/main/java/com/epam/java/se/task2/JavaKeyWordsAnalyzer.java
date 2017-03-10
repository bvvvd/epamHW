package com.epam.java.se.task2;

import java.io.*;
import java.util.*;

public class JavaKeyWordsAnalyzer {
    private Set<String> keywords;
    private Map<String, Integer> analyzedMap = new HashMap<>();
    private final String lineSeparator = System.getProperty("line.separator");

    public JavaKeyWordsAnalyzer() {
        try {
            prepareListWithKeywords();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void makeOutputAnalyzedFile(String fileToAnalyzeName, String outputFileName) throws IOException {
        Objects.requireNonNull(fileToAnalyzeName);

        analyze(fileToAnalyzeName);

        makeOutput(outputFileName);
    }

    private FileReader openFileToRead(String fileToAnalyzeName) throws FileNotFoundException {
        return new FileReader(fileToAnalyzeName);
    }

    private void analyze(String fileName) throws IOException {
        FileReader inputStream = openFileToRead(fileName);
        final String fileText = readFileToString(inputStream);

        final List<String> fileLinesList = formFileTextAsLinesList(fileText);

        evaluateAnalyzedMap(fileLinesList);

    }

    private void evaluateAnalyzedMap(List<String> fileLinesList) {
        fileLinesList.forEach((line) -> keywords.forEach((word) -> {
            if (line.contains(word)) addValueToMap(word);
        }));
    }

    private List<String> formFileTextAsLinesList(String fileText) {
        StringTokenizer tokenizer =
                new StringTokenizer(fileText, lineSeparator);

        List<String> textAsLines = new ArrayList<>();
        while (tokenizer.hasMoreTokens()) {
            textAsLines.add(tokenizer.nextToken());
        }

        return textAsLines;
    }

    private String readFileToString(FileReader inputStream) throws IOException {
        final StringBuilder javaFileTextBuilder = new StringBuilder();

        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNextLine()) {
            javaFileTextBuilder
                    .append(scanner.nextLine())
                    .append(lineSeparator);
        }

        return javaFileTextBuilder.toString();
    }

    private void addValueToMap(String word) {
        if (analyzedMap.containsKey(word)) {
            analyzedMap.put(word, analyzedMap.get(word) + 1);
        } else {
            analyzedMap.put(word, 1);
        }
    }

    private void prepareListWithKeywords() throws IOException {
        final FileReader keywordInput = openFileToRead("keywords.txt");

        final String keywordsAsString = readFileToString(keywordInput);
        Set<String> keywordSet = new HashSet<>();

        StringTokenizer tokenizer =
                new StringTokenizer(keywordsAsString, lineSeparator);

        while (tokenizer.hasMoreTokens()) {
            keywordSet.add(tokenizer.nextToken());
        }

        this.keywords = keywordSet;
    }

    private void makeOutput(String outputFileName) throws IOException {
        final String outputString = formStringToWriteOutputFile();
        final FileWriter fileWriter = new FileWriter(outputFileName);
        fileWriter.write(outputString);
        fileWriter.close();
    }

    private String formStringToWriteOutputFile() {
        final StringBuilder outputStringBuilder = new StringBuilder();

        for (Map.Entry<String, Integer> entry : analyzedMap.entrySet()) {
            outputStringBuilder
                    .append(entry.getKey())
                    .append(" ")
                    .append(entry.getValue())
                    .append(lineSeparator);
        }

        return outputStringBuilder.toString();
    }
}

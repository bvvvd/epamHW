package com.epam.java.se;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

public class JavaKeyWordsAnalyzer {
    private FileInputStream inputStream;
    private List<String> keywords;

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

    private void analyze() {

    }

    private void prepareListWithKeywords() throws IOException {
        final StringBuilder keywordsBuilder = new StringBuilder();
        int readSymbol;
        List<String> keywordList = new ArrayList<>();
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

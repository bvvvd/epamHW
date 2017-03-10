package com.epam.java.se;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Objects;

public class JavaKeyWordsAnalyzer {
    private FileInputStream inputStream;

    public void makeOutputAnalyzedFile(String fileToAnalyzeName) throws FileNotFoundException {
        Objects.requireNonNull(fileToAnalyzeName);

        openFileToAnalyze(fileToAnalyzeName);

        analyzeFile();
        makeOutput();
    }

    private void openFileToAnalyze(String fileToAnalyzeName) throws FileNotFoundException {
        this.inputStream = new FileInputStream(fileToAnalyzeName);
    }

    private void analyzeFile() {

    }

    private void makeOutput() {

    }
}

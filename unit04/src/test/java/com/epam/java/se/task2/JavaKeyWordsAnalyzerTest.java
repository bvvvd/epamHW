package com.epam.java.se.task2;

import org.junit.Test;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;

public class JavaKeyWordsAnalyzerTest {
    private final String inputFileName = "WritingStationeryItem.java";
    private final String outputFileName = "outputTask2.txt";
    private final String keyWordsfileName = "keywords.txt";
    private final String lineSeparator = System.getProperty("line.separator");

    @Test(expected = NullPointerException.class)
    public void testThatAnalyzerFailsWithNullJavaFileArgument() throws Exception {
        final JavaKeyWordsAnalyzer analyzer = new JavaKeyWordsAnalyzer();
        analyzer.makeOutputAnalyzedFile(null, null);
    }

    @Test(expected = FileNotFoundException.class)
    public void testThatAnalyzerFailsWithNotExistingFile() throws Exception {
        final JavaKeyWordsAnalyzer analyzer = new JavaKeyWordsAnalyzer();
        analyzer.makeOutputAnalyzedFile("wrong file", outputFileName);
    }

    @Test
    public void testThatAnalyzerWorksCorrectly() throws Exception {
        final JavaKeyWordsAnalyzer analyzer = new JavaKeyWordsAnalyzer();
        analyzer.makeOutputAnalyzedFile(inputFileName, outputFileName);
    }
}

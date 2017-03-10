package com.epam.java.se.task1;

import com.epam.java.se.task2.JavaKeyWordsAnalyzer;
import org.junit.Test;

import java.io.FileNotFoundException;

public class JavaKeyWordsAnalyzerTest {
    private final String inputFileName = "WritingStationeryItem.java";
    private final String outputFileName = "output.txt";
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
        analyzer.makeOutputAnalyzedFile("1", outputFileName);
    }

    @Test
    public void testThatAnalyzerWorksCorrectly() throws Exception {
        final JavaKeyWordsAnalyzer analyzer = new JavaKeyWordsAnalyzer();
        analyzer.makeOutputAnalyzedFile(inputFileName, outputFileName);
    }
}

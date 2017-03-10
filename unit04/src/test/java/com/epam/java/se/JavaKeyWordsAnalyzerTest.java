package com.epam.java.se;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class JavaKeyWordsAnalyzerTest {
    private final String inputFileName = "WritingStationeryItem.java";
    private final String outputFileName = "output.txt";
    private final String keyWordsfileName = "keywords.txt";

    @Test(expected = NullPointerException.class)
    public void testThatAnalyzerFailsWithNullJavaFileArgument() throws Exception {
        final JavaKeyWordsAnalyzer analyzer = new JavaKeyWordsAnalyzer();
        analyzer.makeOutputAnalyzedFile(null);
    }

    @Test(expected = FileNotFoundException.class)
    public void testThatAnalyzerFailsWithNotExistingFile() throws Exception {
        final JavaKeyWordsAnalyzer analyzer = new JavaKeyWordsAnalyzer();
        analyzer.makeOutputAnalyzedFile("1");
    }

    @Test
    public void testThatAnalyzerWorksCorrectly() throws Exception {
        final JavaKeyWordsAnalyzer analyzer = new JavaKeyWordsAnalyzer();
        analyzer.makeOutputAnalyzedFile(inputFileName);
    }
}

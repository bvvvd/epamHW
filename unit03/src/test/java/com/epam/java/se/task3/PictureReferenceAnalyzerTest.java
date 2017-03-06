package com.epam.java.se.task3;

import org.junit.Test;

import java.io.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class PictureReferenceAnalyzerTest {
    private String fileName = "article.html";

    @Test
    public void voidTestThatWeCanOpenHtmlFileToAnalyze() {
        final PictureReferenceAnalyzer analyzer = new PictureReferenceAnalyzer(fileName);

        final File file = new File(fileName);

        assertThat(analyzer.getFile().equals(file), is(true));
    }

    @Test
    public void testThatWeCanParseHtmlFileToAnalyze() throws IOException {
        final PictureReferenceAnalyzer analyzer = new PictureReferenceAnalyzer(fileName);
        final String parsedHtmlTextByAnalyzer = analyzer.extractSourceText();

        final BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(new FileInputStream(fileName), "windows-1251"));
        final StringBuilder realHtmlText = new StringBuilder();
        bufferedReader.lines().forEach(realHtmlText::append);

        assertThat(parsedHtmlTextByAnalyzer.equals(realHtmlText.toString()), is(true));
    }
}
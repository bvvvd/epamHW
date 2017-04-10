package com.epam.java.se.task3;

import org.junit.Test;

import java.io.*;
import java.util.List;

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

    @Test
    public void testWeCanExtractArticleTextCorrectly() throws FileNotFoundException, UnsupportedEncodingException {
        final PictureReferenceAnalyzer analyzer = new PictureReferenceAnalyzer(fileName);
        final List<String> parsedTextWithoutHtmlByAnalyzer = analyzer.extractTextWithoutHtml();

        parsedTextWithoutHtmlByAnalyzer.forEach(System.out::println);

        assertThat(parsedTextWithoutHtmlByAnalyzer.
                get(1).
                contains("Экстренное сообщение учёным мирового научного сообщества о революционном открытии в науке"),
                is(true));

        assertThat(parsedTextWithoutHtmlByAnalyzer.
                get(parsedTextWithoutHtmlByAnalyzer.size() - 1).
                contains("между ядрами атомов углерода в магнетонах, определяют длину сторон правильного шестиугольника."),
                is(true) );
        analyzer.makeOutputHtml();
    }
}
package com.epam.java.se.task3;

import java.io.*;

public class PictureReferenceAnalyzer {
    private final File file;

    public PictureReferenceAnalyzer(String fileName) {
        this.file = new File(fileName);
    }

    public String extractSourceText() throws FileNotFoundException, UnsupportedEncodingException {
        final BufferedReader htmlTextReader = new BufferedReader(
                new InputStreamReader(new FileInputStream(fileName()), "windows-1251"));
        final StringBuilder htmlText = new StringBuilder();
        htmlTextReader.lines().forEach(htmlText::append);

        return htmlText.toString();
    }

    private String fileName() {
        return file.getName();
    }

    public File getFile() {
        return new File(String.valueOf(file));
    }
}

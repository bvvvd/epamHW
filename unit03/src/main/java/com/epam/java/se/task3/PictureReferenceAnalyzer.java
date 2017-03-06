package com.epam.java.se.task3;

import java.io.BufferedReader;
import java.io.File;

public class PictureReferenceAnalyzer {
    private final File file;

    public PictureReferenceAnalyzer(String fileName) {
        this.file = new File(fileName);
    }

    public String extractSourceText() {
//        final BufferedReader
        return null;
    }

    public File getFile() {
        return new File(String.valueOf(file));
    }
}

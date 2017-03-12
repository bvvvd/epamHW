package com.epam.java.se;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class FileContentEditor {
    private String lineSeparator = System.getProperty("line.separator");

    public String cat(String fileToGetContent) throws FileNotFoundException {

        final BufferedReader reader = new BufferedReader(new FileReader(fileToGetContent));
        final StringBuilder stringFileContentBuilder = new StringBuilder();
        reader.lines().forEach((currentLine) -> stringFileContentBuilder.append(currentLine).append(lineSeparator));

        return stringFileContentBuilder.toString();
    }
}

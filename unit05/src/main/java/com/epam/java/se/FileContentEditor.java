package com.epam.java.se;

import java.io.*;
import java.util.Objects;

public class FileContentEditor {
    private String lineSeparator = System.getProperty("line.separator");

    public String cat(String fileToGetContent) throws FileNotFoundException {
        Objects.requireNonNull(fileToGetContent);

        final BufferedReader reader = new BufferedReader(new FileReader(fileToGetContent));
        final StringBuilder stringFileContentBuilder = new StringBuilder();
        reader.lines().forEach((currentLine) -> stringFileContentBuilder.append(currentLine).append(lineSeparator));

        return stringFileContentBuilder.toString();
    }

    public void cat(String fileToWriteContentName, String content) throws IOException {
        Objects.requireNonNull(fileToWriteContentName);
        Objects.requireNonNull(content);

        final BufferedWriter writer = new BufferedWriter(new FileWriter(fileToWriteContentName));

        writer.write(content);
        writer.flush();
        writer.close();
    }

    public void catt(String fileToAppendName, String contentToAppend) throws IOException {
        Objects.requireNonNull(fileToAppendName);
        Objects.requireNonNull(contentToAppend);

        final BufferedWriter writer = new BufferedWriter(new FileWriter(fileToAppendName, true));

        writer.append(lineSeparator)
                .append(contentToAppend);

        writer.flush();
        writer.close();
    }
}

package com.epam.java.se.task3;

import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class Recoder {

    private final String fileName;
    private final String sourceCode;

    private final String lineSeparator = System.getProperty("line.separator");

    public Recoder(String fileName, String sourceCode) {
        Objects.requireNonNull(fileName);
        Objects.requireNonNull(sourceCode);

        this.fileName = fileName;
        this.sourceCode = sourceCode;
    }

    public void rewriteWithNewCode(String outputFileName, String newCode) throws IOException {
        final String fileContent = readFileToString();

        writeToOutputFile(fileContent, outputFileName, newCode);
    }

    private void writeToOutputFile(String fileContent, String outputFileName, String newCode) throws IOException {
        final OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(outputFileName), newCode);
        writer.write(fileContent);
        writer.close();
    }

    private String readFileToString() throws FileNotFoundException {
        final StringBuilder javaFileTextBuilder = new StringBuilder();

        Scanner scanner = new Scanner(new FileReader(fileName));
        while (scanner.hasNextLine()) {
            javaFileTextBuilder
                    .append(scanner.nextLine())
                    .append(lineSeparator);
        }

        return javaFileTextBuilder.toString();
    }
}
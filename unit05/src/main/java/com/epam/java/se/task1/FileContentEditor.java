package com.epam.java.se.task1;

import java.io.*;
import java.util.Objects;

/**
 * Class provides primitive method kit for writing and reading interaction with files.
 *
 * @author Valeriy Burmistrov
 */
public class FileContentEditor {
    private String lineSeparator = System.getProperty("line.separator");


    /**
     * Creates a string similar to content of the file with specified name.
     *
     * @param fileToGetContent name of file to read
     * @return string of file's content
     * @throws FileNotFoundException if specified file does not exist
     */
    public String cat(String fileToGetContent) throws FileNotFoundException {
        Objects.requireNonNull(fileToGetContent);

        final BufferedReader reader = new BufferedReader(new FileReader(fileToGetContent));
        final StringBuilder stringFileContentBuilder = new StringBuilder();
        reader.lines().forEach((currentLine) -> stringFileContentBuilder.append(currentLine).append(lineSeparator));

        return stringFileContentBuilder.toString();
    }

    /**
     * Writes a given string of content in file with specified name. If the file does not exist,
     * creates a new file with specified name, if it's possible.
     *
     * @param fileToWriteContentName file to be filled
     * @param content content to be written
     * @throws IOException if it's impossible to create file
     * @throws NullPointerException if arguments are null
     */
    public void cat(String fileToWriteContentName, String content) throws IOException {
        Objects.requireNonNull(fileToWriteContentName);
        Objects.requireNonNull(content);

        final BufferedWriter writer = new BufferedWriter(new FileWriter(fileToWriteContentName));

        writer.write(content);
        writer.flush();
        writer.close();
    }

    /**
     * Append given content to the file with specified name.f the file does not exist,
     * creates a new file with specified name, if it's possible.
     *
     * @param fileToAppendName name of file to be filled
     * @param contentToAppend content to be written
     * @throws NullPointerException if arguments are null
     * @throws IOException if it's impossible to create file
     */
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

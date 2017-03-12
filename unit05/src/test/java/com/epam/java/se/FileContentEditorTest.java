package com.epam.java.se;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FileContentEditorTest {

    private String lineSeparator = System.getProperty("line.separator");

    @Test
    public void testWeCanExtractContentFromExistingFile() throws FileNotFoundException {
        final FileContentEditor contentEditor = new FileContentEditor();

        final String fileContent = contentEditor.cat("C:\\WritingStationeryItem.java");

        final BufferedReader reader = new BufferedReader(new FileReader("C:\\WritingStationeryItem.java"));
        final StringBuilder builder = new StringBuilder();
        reader.lines().forEach((line) -> builder.append(line).append(lineSeparator));

        assertThat(fileContent.equals(builder.toString()), is(true));
    }
}

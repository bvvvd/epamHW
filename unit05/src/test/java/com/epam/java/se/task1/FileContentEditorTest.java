package com.epam.java.se.task1;

import com.epam.java.se.task1.exceptions.DirectoryRemovingException;
import com.epam.java.se.task1.exceptions.FileNotExistException;
import org.junit.Test;

import java.io.*;

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

    @Test(expected = FileNotFoundException.class)
    public void testTryingToExtractContentFromNotExistingFileThrowsFileNotFoundException() throws FileNotFoundException {
        final FileContentEditor contentEditor = new FileContentEditor();

        final String fileContent = contentEditor.cat("C:\\notexistingfile");
    }

    @Test
    public void testWeCanWriteContentInExistingFile() throws IOException, FileNotExistException, DirectoryRemovingException {
        final FileContentEditor contentEditor = new FileContentEditor();

        contentEditor.cat("C:\\test\\cat1test.txt", "im trying to write this");
        final String fileContent = contentEditor.cat("C:\\test\\cat1test.txt");

        final BufferedReader reader = new BufferedReader(new FileReader("C:\\test\\cat1test.txt"));
        final StringBuilder builder = new StringBuilder();
        reader.lines().forEach((line) -> builder.append(line).append(lineSeparator));

        final FileEditor editor = new FileEditor();
        editor.rm("C:\\test\\cat1test.txt");
        assertThat(fileContent.equals(builder.toString()), is(true));

    }

    @Test
    public void testWeCanAppendContentInExistingFile() throws IOException, FileNotExistException, DirectoryRemovingException {

        final FileContentEditor contentEditor = new FileContentEditor();

        contentEditor.cat("C:\\test\\cat2test.txt", "im trying to write this");
        contentEditor.catt("C:\\test\\cat2test.txt", "im trying to write this one more time");
        final String fileContent = contentEditor.cat("C:\\test\\cat2test.txt");

        final BufferedReader reader = new BufferedReader(new FileReader("C:\\test\\cat2test.txt"));
        final StringBuilder builder = new StringBuilder();
        reader.lines().forEach((line) -> builder.append(line).append(lineSeparator));

        final FileEditor editor = new FileEditor();
        editor.rm("C:\\test\\cat2test.txt");
        assertThat(fileContent.equals(builder.toString()), is(true));


    }

    @Test(expected = NullPointerException.class)
    public void testThatTryingToWriteContentToFileWithNullFileNameThrowsNPE() throws IOException {
        final FileContentEditor contentEditor = new FileContentEditor();

        contentEditor.cat(null, "C:\\WritingStationeryItem.java");
    }

    @Test(expected = NullPointerException.class)
    public void testThatTryingToWriteContentToFileWithNullContentThrowsNPE() throws IOException {
        final FileContentEditor contentEditor = new FileContentEditor();

        contentEditor.cat("C:\\WritingStationeryItem.java", null);
    }


    @Test(expected = NullPointerException.class)
    public void testThatTryingToAppendContentToFileWithNullFileNameThrowsNPE() throws IOException {
        final FileContentEditor contentEditor = new FileContentEditor();

        contentEditor.catt(null, "C:\\WritingStationeryItem.java");
    }

    @Test(expected = NullPointerException.class)
    public void testThatTryingToAppendContentToFileWithNullContentThrowsNPE() throws IOException {
        final FileContentEditor contentEditor = new FileContentEditor();

        contentEditor.catt("C:\\WritingStationeryItem.java", null);
    }

}

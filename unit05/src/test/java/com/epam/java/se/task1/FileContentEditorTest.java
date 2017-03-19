package com.epam.java.se.task1;

import com.epam.java.se.task1.exceptions.DirectoryRemovingException;
import com.epam.java.se.task1.exceptions.FileNotExistException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FileContentEditorTest {

    private String lineSeparator = System.getProperty("line.separator");
    private FileContentEditor contentEditor;
    private String temporaryDirectoryPath;

    @Before
    public void init() {
        this.contentEditor = new FileContentEditor();
        this.temporaryDirectoryPath = temporaryTestDirectory.getRoot().getAbsolutePath();
    }

    @Rule
    public TemporaryFolder temporaryTestDirectory = new TemporaryFolder();

    @Test
    public void testWeCanExtractContentFromExistingFile() throws IOException {
        temporaryTestDirectory.newFile("test.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(temporaryDirectoryPath.concat("\\test.txt")));
        writer.write("some testing text".concat(lineSeparator).concat("still some testing test"));

        final String fileContent = contentEditor.cat(temporaryDirectoryPath.concat("\\test.txt"));

        final BufferedReader reader = new BufferedReader(new FileReader(temporaryDirectoryPath.concat("\\test.txt")));
        final StringBuilder builder = new StringBuilder();
        reader.lines().forEach((line) -> builder.append(line).append(lineSeparator));

        assertThat(fileContent.equals(builder.toString()), is(true));
    }

    @Test(expected = FileNotFoundException.class)
    public void testTryingToExtractContentFromNotExistingFileThrowsFileNotFoundException() throws FileNotFoundException {
        final String fileContent = contentEditor.cat(temporaryDirectoryPath.concat("\\notexistingfile.txt"));
    }

    @Test
    public void testWeCanWriteContentInExistingFile() throws IOException, FileNotExistException, DirectoryRemovingException {
        contentEditor.cat(temporaryDirectoryPath.concat("\\cat1test.txt"), "im trying to write this");
        final String fileContent = contentEditor.cat(temporaryDirectoryPath.concat("\\cat1test.txt"));

        final BufferedReader reader = new BufferedReader(new FileReader(temporaryDirectoryPath.concat("\\cat1test.txt")));
        final StringBuilder builder = new StringBuilder();
        reader.lines().forEach((line) -> builder.append(line).append(lineSeparator));

        assertThat(fileContent.equals(builder.toString()), is(true));
    }

    @Test
    public void testWeCanAppendContentInExistingFile() throws IOException, FileNotExistException, DirectoryRemovingException {
        contentEditor.cat(temporaryDirectoryPath.concat("\\cat2test.txt"), "im trying to write this");
        contentEditor.catt(temporaryDirectoryPath.concat("\\cat2test.txt"), "im trying to write this one more time");
        final String fileContent = contentEditor.cat(temporaryDirectoryPath.concat("\\cat2test.txt"));

        final BufferedReader reader = new BufferedReader(new FileReader(temporaryDirectoryPath.concat("\\cat2test.txt")));
        final StringBuilder builder = new StringBuilder();
        reader.lines().forEach((line) -> builder.append(line).append(lineSeparator));

        assertThat(fileContent.equals(builder.toString()), is(true));
    }

    @Test(expected = NullPointerException.class)
    public void testThatTryingToWriteContentToFileWithNullFileNameThrowsNPE() throws IOException {
        contentEditor.cat(null, temporaryDirectoryPath.concat("test.txt"));
    }

    @Test(expected = NullPointerException.class)
    public void testThatTryingToWriteContentToFileWithNullContentThrowsNPE() throws IOException {
        contentEditor.cat(temporaryDirectoryPath.concat("test.txt"), null);
    }


    @Test(expected = NullPointerException.class)
    public void testThatTryingToAppendContentToFileWithNullFileNameThrowsNPE() throws IOException {
        contentEditor.catt(null, temporaryDirectoryPath.concat("test.txt"));
    }

    @Test(expected = NullPointerException.class)
    public void testThatTryingToAppendContentToFileWithNullContentThrowsNPE() throws IOException {
        contentEditor.catt(temporaryDirectoryPath.concat("test.txt"), null);
    }

}

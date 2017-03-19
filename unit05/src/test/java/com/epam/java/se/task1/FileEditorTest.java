package com.epam.java.se.task1;

import com.epam.java.se.task1.exceptions.DirectoryRemovingException;
import com.epam.java.se.task1.exceptions.FileNotExistException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FileEditorTest {
    private FileEditor editor;
    private String temporaryDirectoryPath;

    @Before
    public void init() {
        this.editor = new FileEditor();
        this.temporaryDirectoryPath = temporaryTestDirectory.getRoot().getAbsolutePath();
    }

    @Rule
    public TemporaryFolder temporaryTestDirectory = new TemporaryFolder();

    @Test
    public void testThatWeCanCreateFileWithSpecifiedName() throws IOException, FileNotExistException, DirectoryRemovingException, InterruptedException {
        editor.touch(temporaryDirectoryPath.concat("\\test.txt"));

        final File fileToCheckExistence = new File(temporaryDirectoryPath.concat("\\test.txt"));

        assertThat(fileToCheckExistence.exists(), is(true));
    }

    @Test(expected = FileAlreadyExistsException.class)
    public void testTryingToCreateExistingFileThrowsFileAlreadyExistsException()
            throws IOException, FileNotExistException, DirectoryRemovingException {

        editor.touch(temporaryDirectoryPath.concat("\\test.txt"));

        editor.touch(temporaryDirectoryPath.concat("\\test.txt"));
    }

    @Test
    public void testThatWeCanRemoveFile() throws FileNotExistException, IOException, DirectoryRemovingException {
        editor.touch(temporaryDirectoryPath.concat("\\test.txt"));
        editor.rm(temporaryDirectoryPath.concat("\\test.txt"));

        final File fileToCheckExistence = new File(temporaryDirectoryPath.concat("\\test.txt"));
        assertThat(fileToCheckExistence.exists(), is(false));
    }

    @Test(expected = FileNotExistException.class)
    public void testThatTryingToRemoveNotExistingFileThrowsFileNotExistException()
            throws FileNotExistException, DirectoryRemovingException {
        editor.rm(temporaryDirectoryPath.concat("\\test.txt"));
    }

    @Test
    public void testThatWeCanCreateDirectory()
            throws FileAlreadyExistsException, FileNotExistException, DirectoryRemovingException {
        editor.mkdir(temporaryDirectoryPath.concat("\\test"));

        final File directoryToCheckCreating = new File(temporaryDirectoryPath.concat("\\test"));
        assertThat(directoryToCheckCreating.exists() && directoryToCheckCreating.isDirectory(), is(true));
    }

    @Test
    public void testThatWeCanRemoveDirectory() throws
            FileAlreadyExistsException, FileNotExistException, DirectoryRemovingException {
        editor.mkdir(temporaryDirectoryPath.concat("\\test"));
        editor.rm(temporaryDirectoryPath.concat("\\test"));

        final File directoryToCheckExistence = new File(temporaryDirectoryPath.concat("\\test"));
        assertThat(directoryToCheckExistence.exists(), is(false));
    }

    @Test(expected = DirectoryRemovingException.class)
    public void testThatTryingToRemoveNotEmptyDirectoryThrowsDirectoryRemovingException()
            throws IOException, FileNotExistException, DirectoryRemovingException {
        editor.mkdir(temporaryDirectoryPath.concat("\\test"));
        editor.touch(temporaryDirectoryPath.concat("\\test\\test.txt"));
        editor.rm(temporaryDirectoryPath.concat("\\test"));
    }

    @Test(expected = IOException.class)
    public void testThatTryingToCreateFileInRootDirectoryThrowsIOExceptionCauseOfLackOfRights() throws IOException {
        final String rootPath = File.listRoots()[0].getAbsolutePath();
        editor.touch(rootPath);
    }

    @Test(expected = FileAlreadyExistsException.class)
    public void testTryingToCreateExistingDirectoryThrowsFileAlreadyExistsException()
            throws IOException, FileNotExistException, DirectoryRemovingException {
        editor.mkdir(temporaryDirectoryPath.concat("\\test"));
        editor.mkdir(temporaryDirectoryPath.concat("\\test"));
    }

    @Test(expected = NullPointerException.class)
    public void testThatTryingToCreateFileWithNullArgumentThrowsNPE() throws IOException {
        editor.touch(null);
    }

    @Test(expected = NullPointerException.class)
    public void testThatTryingToRemoveFileWithNullArgumentThrowsNPE() throws IOException, FileNotExistException, DirectoryRemovingException {
        editor.rm(null);
    }

    @Test(expected = NullPointerException.class)
    public void testThatTryingToCreateDirectoryWithNullArgumentThrowsNPE() throws IOException {
        editor.mkdir(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThatTryingToCreateDirectoryWithNotExistingParentThrowsIllegalArgumentException() throws FileAlreadyExistsException {
        editor.mkdir(temporaryDirectoryPath.concat("\\notexistingparent\\test"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThatTryingToCreateDirectoryWithExistingParentWhichIsAFileThrowsIllegalArgumentException() throws IOException, FileNotExistException, DirectoryRemovingException {
        editor.mkdir(temporaryDirectoryPath.concat("\\existingparent.txt\\test"));
        editor.mkdir(temporaryDirectoryPath.concat("\\existingparent.txt\\test"));
    }
}

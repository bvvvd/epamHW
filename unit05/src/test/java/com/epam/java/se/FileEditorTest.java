package com.epam.java.se;

import com.epam.java.se.exceptions.DirectoryRemovingException;
import com.epam.java.se.exceptions.FileNotExistException;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FileEditorTest {

    @Test
    public void testThatWeCanCreateFileWithSpecifiedName() throws IOException, FileNotExistException, DirectoryRemovingException {
        final FileEditor editor = new FileEditor();
        editor.touch("C:\\test\\test1.txt");

        final File fileToCheckExistence = new File("C:\\test\\test1.txt");
        assertThat(fileToCheckExistence.exists(), is(true));

        editor.rm("C:\\test\\test1.txt");
    }

    @Test(expected = FileAlreadyExistsException.class)
    public void testTryingToCreateExistingFileThrowsFileAlreadyExistsException()
            throws IOException, FileNotExistException, DirectoryRemovingException {
        final FileEditor editor = new FileEditor();
        editor.touch("C:\\test\\test.txt");
        try {
            editor.touch("C:\\test\\test.txt");
        } catch (FileAlreadyExistsException e) {
            editor.rm("C:\\test\\test.txt");
            throw e;
        }
    }

    @Test
    public void testThatWeCanRemoveFile() throws FileNotExistException, IOException, DirectoryRemovingException {
        final FileEditor editor = new FileEditor();

        editor.touch("C:\\test\\test2.txt");
        editor.rm("C:\\test\\test2.txt");

        final File fileToCheckExistence = new File("C:\\test\\test2.txt");
        assertThat(fileToCheckExistence.exists(), is(false));
    }

    @Test(expected = FileNotExistException.class)
    public void testThatTryingToRemoveNotExistingFileThrowsFileNotExistException()
            throws FileNotExistException, DirectoryRemovingException {
        final FileEditor editor = new FileEditor();

        editor.rm("C:\\test\\test3.txt");
    }

    @Test
    public void testThatWeCanCreateDirectory()
            throws FileAlreadyExistsException, FileNotExistException, DirectoryRemovingException {
        final FileEditor editor = new FileEditor();

        editor.mkdir("C:\\test\\test4\\");

        final File directoryToCheckCreating = new File("C:\\test\\test4\\");
        assertThat(directoryToCheckCreating.exists() && directoryToCheckCreating.isDirectory(), is(true));
        editor.rm("C:\\test\\test4\\");
    }

    @Test
    public void testThatWeCanRemoveDirectory() throws
            FileAlreadyExistsException, FileNotExistException, DirectoryRemovingException {
        final FileEditor editor = new FileEditor();

        editor.mkdir("C:\\test\\test5\\");
        editor.rm("C:\\test\\test5\\");

        final File directoryToCheckExistence = new File("C:\\test5\\");
        assertThat(directoryToCheckExistence.exists(), is(false));
    }

    @Test(expected = DirectoryRemovingException.class)
    public void testThatTryingToRemoveNotEmptyDirectoryThrowsDirectoryRemovingException()
            throws IOException, FileNotExistException, DirectoryRemovingException {
        final FileEditor editor = new FileEditor();

        editor.mkdir("C:\\test\\test6\\");
        editor.touch("C:\\test\\test6\\1.txt");
        try {
            editor.rm("C:\\test\\test6\\");
        } catch (DirectoryRemovingException e) {
            editor.rm("C:\\test\\test6\\1.txt");
            editor.rm("C:\\test\\test6\\");
            throw e;
        }
    }

    @Test(expected = IOException.class)
    public void testThatTryingToCreateFileInRootDirectoryThrowsIOExceptionCauseOfLackOfRights() throws IOException {
        final FileEditor editor = new FileEditor();

        editor.touch("C:\\test.txt");
    }

    @Test(expected = FileAlreadyExistsException.class)
    public void testTryingToCreateExistingDirectoryThrowsFileAlreadyExistsException()
            throws IOException, FileNotExistException, DirectoryRemovingException {
        final FileEditor editor = new FileEditor();
        editor.touch("C:\\test\\test\\");
        try {
            editor.touch("C:\\test\\test\\");
        } catch (FileAlreadyExistsException e) {
            editor.rm("C:\\test\\test\\");
            throw e;
        }
    }

    @Test(expected = NullPointerException.class)
    public void testThatTryingToCreateFileWithNullArgumentThrowsNPE() throws IOException {
        final FileEditor editor = new FileEditor();

        editor.touch(null);
    }

    @Test(expected = NullPointerException.class)
    public void testThatTryingToRemoveFileWithNullArgumentThrowsNPE() throws IOException, FileNotExistException, DirectoryRemovingException {
        final FileEditor editor = new FileEditor();

        editor.rm(null);
    }

    @Test(expected = NullPointerException.class)
    public void testThatTryingToCreateDirectoryWithNullArgumentThrowsNPE() throws IOException {
        final FileEditor editor = new FileEditor();

        editor.mkdir(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThatTryingToCreateDirectoryWithNotExistingParentThrowsIllegalArgumentException() throws FileAlreadyExistsException {
        final FileEditor editor = new FileEditor();

        editor.mkdir("C:\\notexistingparent\\try");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThatTryingToCreateDirectoryWithExistingParentWhichIsAFileThrowsIllegalArgumentException() throws IOException {
        final FileEditor editor = new FileEditor();

        editor.touch("C:\\existingparent.txt\\");
        editor.mkdir("C:\\existingparent.txt\\try");
    }
}

package com.epam.java.se;

import com.epam.java.se.exceptions.FileNotExistException;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FileEditorTest {

    @Test
    public void testThatWeCanCreateFileWithSpecifiedName() throws IOException {
        final FileEditor editor = new FileEditor();
        editor.touch("C:\\test\\test.txt");

        final File fileToCheckExistence = new File("C:\\test\\test.txt");
        assertThat(fileToCheckExistence.exists(), is(true));
    }

    @Test
    public void testThatWeCanRemoveFile() throws FileNotExistException, IOException {
        final FileEditor editor = new FileEditor();

        editor.touch("C:\\test\\test.txt");
        editor.rm("C:\\test\\test.txt");

        final File fileToCheckExistence = new File("C:\\test\\test.txt");
        assertThat(fileToCheckExistence.exists(), is(false));
    }

    @Test(expected = FileNotExistException.class)
    public void testThatTryingToRemoveNotExistingFileOrDirectoryThrowsFileNotExistException() throws FileNotExistException {
        final FileEditor editor = new FileEditor();

        editor.rm("C:\\test\\test.txt");
    }
}

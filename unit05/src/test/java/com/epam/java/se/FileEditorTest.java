package com.epam.java.se;

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

        final File fileToCheckExistance = new File("C:\\test\\test.txt");
        assertThat(fileToCheckExistance.exists(), is(true));
    }
}

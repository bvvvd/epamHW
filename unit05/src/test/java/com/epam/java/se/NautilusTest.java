package com.epam.java.se;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class NautilusTest {

    @Test
    public void testThatWeCanCreateListOfDirectoryContent() throws IOException {
        final Nautilus nautilus = new Nautilus();
        final File[] listOfContent = nautilus.ls(new File("C:\\"));

        assertThat(Arrays.equals(listOfContent, new File("C:\\").listFiles()), is(true));
    }
}

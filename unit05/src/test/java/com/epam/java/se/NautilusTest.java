package com.epam.java.se;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class NautilusTest {

    @Test
    public void testThatWeCanCreateCatalogOfDirectoryContent() throws IOException {
        final Nautilus nautilus = new Nautilus();
        final File[] catalogOfContent = nautilus.ls(new File("C:\\"));

        assertThat(Arrays.equals(catalogOfContent, new File("C:\\").listFiles()), is(true));
    }

    @Test
    public void testThatCatalogOfDirectoryContentReturnEmptyArrayIfDirectoryIsEmpty() {
        final Nautilus nautilus = new Nautilus();
        final File[] catalogOfContent = nautilus.ls(new File("C:\\test\\"));

        assertThat(catalogOfContent.length, is(0));
    }

    @Test
    public void 
}

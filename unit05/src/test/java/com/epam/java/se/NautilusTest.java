package com.epam.java.se;

import com.epam.java.se.exceptions.FileNotExistException;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class NautilusTest {
    private String homeDirectory = System.getProperty("user.home");

    @Test
    public void testThatWeCanCreateCatalogOfDirectoryContent() throws IOException {
        final Nautilus nautilus = new Nautilus();
        final File[] catalogOfContent = nautilus.ls();

        assertThat(Arrays.equals(catalogOfContent, new File(System.getProperty("user.home")).listFiles()), is(true));
    }

    @Test
    public void testThatCatalogOfDirectoryContentReturnEmptyArrayIfDirectoryIsEmpty() {
        final Nautilus nautilus = new Nautilus();

        try {
            try {
                nautilus.cd("C:\\emptyDirectory\\");
            } catch (FileNotExistException e) {
                e.printStackTrace();
            }
        } catch (IllegalArgumentException e) {

        }

        final File[] catalogOfContent = nautilus.ls();

        assertThat(catalogOfContent.length, is(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThatTryingToChangeDirectoryToNotDirectoryThrowsIllegalArgumentException() {
        final Nautilus nautilus = new Nautilus();

        try {
            nautilus.cd("C:\\uTorrent.exe");
        } catch (FileNotExistException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = FileNotExistException.class)
    public void testThatTryingToChangeDirectoryToNotExistingDirectoryThrowsFileNotFoundException() throws FileNotExistException {
        final Nautilus nautilus = new Nautilus();

        nautilus.cd("C:\\asdasdasd");
    }

    @Test
    public void testThatCdMethodWithNullArgumentChangeDirectoryToHomeDirectory() throws FileNotExistException {
        final Nautilus nautilus = new Nautilus();

        nautilus.cd("C:\\test\\");
        nautilus.cd();

        assertThat(nautilus.pwd().equals(homeDirectory), is(true));
    }

    @Test
    public void testThatCdMethodWithTwoPointsArgumentChangeDirectoryToParentDirectory() throws FileNotExistException {
        final Nautilus nautilus = new Nautilus();

        nautilus.cd("C:\\test\\");
        nautilus.cd("..");

        assertThat(nautilus.pwd().equals("C:\\"), is(true));
    }

    @Test
    public void testThatCdMethodWithTwoPointsArgumentDoesNotChangeDirectoryIfDirectoryIsRootDirectory() throws FileNotExistException {
        final Nautilus nautilus = new Nautilus();

        nautilus.cd("C:\\");
        nautilus.cd("..");

        assertThat(nautilus.pwd().equals("C:\\"), is(true));
    }

    @Test(expected = NullPointerException.class)
    public void testThatTryingToChangeDirectoryWithNullArgumentThrowsNPE() throws FileNotExistException {
        final Nautilus nautilus = new Nautilus();

        nautilus.cd(null);
    }
}

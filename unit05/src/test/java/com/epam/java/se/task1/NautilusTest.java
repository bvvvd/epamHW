package com.epam.java.se.task1;

import com.epam.java.se.task1.exceptions.FileNotExistException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.theInstance;
import static org.hamcrest.MatcherAssert.assertThat;

public class NautilusTest {
    private String homeDirectory = System.getProperty("user.home");
    private Nautilus nautilus;
    private String temporaryDirectoryPath;

    @Before
    public void init() {
        this.nautilus = new Nautilus();
        this.temporaryDirectoryPath = temporaryTestDirectory.getRoot().getAbsolutePath();
    }

    @Rule
    public TemporaryFolder temporaryTestDirectory = new TemporaryFolder();

    @Test
    public void testThatWeCanCreateCatalogOfDirectoryContent() throws IOException {
        final File[] catalogOfContent = nautilus.ls();

        assertThat(Arrays.equals(catalogOfContent, new File(System.getProperty("user.home")).listFiles()), is(true));
    }

    @Test
    public void testThatCatalogOfDirectoryContentReturnEmptyArrayIfDirectoryIsEmpty() throws IllegalArgumentException, IOException, FileNotExistException {
        nautilus.cd(temporaryDirectoryPath);

        final File[] catalogOfContent = nautilus.ls();

        assertThat(catalogOfContent.length, is(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThatTryingToChangeDirectoryToNotDirectoryThrowsIllegalArgumentException() throws IOException {
        try {
            nautilus.cd(temporaryTestDirectory.newFile().getAbsolutePath());
        } catch (FileNotExistException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = FileNotExistException.class)
    public void testThatTryingToChangeDirectoryToNotExistingDirectoryThrowsFileNotFoundException() throws FileNotExistException {
        nautilus.cd("notexisitngdirectory");
    }

    @Test
    public void testThatCdMethodWithNullArgumentChangeDirectoryToHomeDirectory() throws FileNotExistException, IOException {
        nautilus.cd(temporaryTestDirectory.newFolder().getAbsolutePath());
        nautilus.cd();

        assertThat(nautilus.pwd().equals(homeDirectory), is(true));
    }

    @Test
    public void testThatCdMethodWithTwoPointsArgumentChangeDirectoryToParentDirectory() throws FileNotExistException, IOException {
        final String directoryName = temporaryTestDirectory.newFolder("subfolder").getAbsolutePath();

        nautilus.cd(directoryName);
        nautilus.cd("..");

        assertThat(nautilus.pwd().equals(temporaryDirectoryPath), is(true));
    }

    @Test
    public void testThatCdMethodWithTwoPointsArgumentDoesNotChangeDirectoryIfDirectoryIsRootDirectory() throws FileNotExistException {
        final String rootPath = File.listRoots()[0].getAbsolutePath();

        nautilus.cd(rootPath);
        nautilus.cd("..");

        assertThat(nautilus.pwd().equals(rootPath), is(true));
    }

    @Test(expected = NullPointerException.class)
    public void testThatTryingToChangeDirectoryWithNullArgumentThrowsNPE() throws FileNotExistException {
        nautilus.cd(null);
    }
}

package com.epam.java.se;

import com.epam.java.se.exceptions.DirectoryRemovingException;
import com.epam.java.se.exceptions.FileNotExistException;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.Objects;

public class FileEditor {

    public void touch(String fileToCreateName) throws IOException {
        Objects.requireNonNull(fileToCreateName);

        final File fileToCreate = new File(fileToCreateName);

        if (!fileToCreate.createNewFile()) {
            throw new FileAlreadyExistsException(fileToCreateName + " is already exists");
        }
    }

    public void rm(String fileToRemoveName) throws FileNotExistException, DirectoryRemovingException {
        Objects.requireNonNull(fileToRemoveName);

        final File fileToDelete = new File(fileToRemoveName);

        checkThatFileToDeleteExists(fileToDelete);

        checkThatFileIsNotNonEmptyDirectory(fileToDelete);

        fileToDelete.delete();
    }

    private void checkThatFileIsNotNonEmptyDirectory(File fileToDelete) throws DirectoryRemovingException {
        if (fileToDelete.isFile()) {
            return;
        }

        if (fileToDelete.listFiles().length > 0) {
            throw new DirectoryRemovingException("directory " + fileToDelete.getAbsolutePath() + " is not empty");
        }
    }

    private void checkThatFileToDeleteExists(File fileToDelete) throws FileNotExistException {
        if (!fileToDelete.exists()) {
            throw new FileNotExistException("cannot remove " + fileToDelete.getAbsoluteFile() + "no such file or directory");
        }
    }

    public void mkdir(String directoryToCreateName) throws FileAlreadyExistsException {
        Objects.requireNonNull(directoryToCreateName);

        final File directoryToCreate = new File(directoryToCreateName);

        checkExistingDirectoryParent(directoryToCreate);

        checkParentIsDirectory(directoryToCreate);

        if (!directoryToCreate.mkdir()) {
            throw new FileAlreadyExistsException(directoryToCreateName + " is already exists");
        }
    }

    private void checkParentIsDirectory(File directoryToCreate) {
        final File parent = new File(directoryToCreate.getParent());

        if (parent.isFile()) {
            throw new IllegalArgumentException("cannot create directory, "
                    + directoryToCreate.getAbsolutePath()
                    + " the parent is not directory");
        }
    }

    private void checkExistingDirectoryParent(File directoryToCreate) {
        final File parent = new File(directoryToCreate.getParent());

        if (!parent.exists()) {
            throw new IllegalArgumentException(
                    "cannot create directory, "
                            + directoryToCreate.getAbsolutePath()
                            + " hierarсhy is too deep, you need to create upper directories firstly");
        }
    }
}

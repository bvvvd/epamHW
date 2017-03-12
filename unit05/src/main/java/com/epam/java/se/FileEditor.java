package com.epam.java.se;

import com.epam.java.se.exceptions.DirectoryRemovingException;
import com.epam.java.se.exceptions.FileNotExistException;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;

public class FileEditor {

    public void touch(String fileToCreateName) throws IOException {
        final File fileToCreate = new File(fileToCreateName);

        if (!fileToCreate.createNewFile()) {
            throw new FileAlreadyExistsException(fileToCreateName + " is already exists");
        }
    }

    public void rm(String fileToRemoveName) throws FileNotExistException, DirectoryRemovingException {
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
        final File fileToCreate = new File(directoryToCreateName);

        if (!fileToCreate.mkdir()) {
            throw new FileAlreadyExistsException(directoryToCreateName + " is already exists");
        }
    }
}

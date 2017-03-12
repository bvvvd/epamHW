package com.epam.java.se;

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

    public void rm(String fileToRemoveName) throws FileNotExistException {
        final File fileToDelete = new File(fileToRemoveName);

        checkThatFileToDeleteExists(fileToDelete);

        fileToDelete.delete();
    }

    private void checkThatFileToDeleteExists(File fileToDelete) throws FileNotExistException {
        if (!fileToDelete.exists()) {
            throw new FileNotExistException("cannot remove " + fileToDelete.getAbsoluteFile() + "no such file or directory");
        }
    }
}

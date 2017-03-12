package com.epam.java.se;

import com.epam.java.se.exceptions.FileNotExistException;

import java.io.File;
import java.io.FileNotFoundException;

public class Nautilus {

    private String currentDirectory;

    public Nautilus() {
        this.currentDirectory = System.getProperty("user.home");
    }

    public File[] ls() {
        final File directoryToGetContent = new File(currentDirectory);
        return directoryToGetContent.listFiles();
    }

    public void cd(String destinationDirectoryName) throws IllegalArgumentException, FileNotExistException {
        checkDestinationDirectoryIsDirectory(destinationDirectoryName);

        changeDirectory(destinationDirectoryName);
    }

    private void changeDirectory(String destinationDirectoryName) throws FileNotExistException {
        final File destination = new File(destinationDirectoryName);

        if (!destination.exists()) {
            throw new FileNotExistException(destinationDirectoryName + " does not exist");
        }

        currentDirectory = destinationDirectoryName;
    }

    private void checkDestinationDirectoryIsDirectory(String destinationDirectoryName) throws IllegalArgumentException {
        final File fileToCheck = new File(destinationDirectoryName);

        if (fileToCheck.isFile()) {
            throw new IllegalArgumentException(destinationDirectoryName + " is not a directory");
        }
    }
}
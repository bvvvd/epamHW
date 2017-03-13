package com.epam.java.se.task1;

import com.epam.java.se.task1.exceptions.FileNotExistException;

import java.io.File;
import java.util.Objects;

/**
 * Class provide primitive method kit for interaction with file system.
 * Class named as ubuntu's default file manager - Nautilus.
 *
 * @author Valeriy Burmistrov
 */
public class Nautilus {
    private final String homeDirectory = System.getProperty("user.home");
    private String currentDirectory;


    /**
     * Create new exemplar of Nautilus with user home directory as current working directory
     */
    public Nautilus() {
        currentDirectory = homeDirectory;
    }

    /**
     * Creates array of files and directories, containing in current working directory.
     *
     * @return array of files and directories
     */
    public File[] ls() {
        final File directoryToGetContent = new File(currentDirectory);

        return directoryToGetContent.listFiles();
    }


    /**
     * Changes current working directory on specified directory.
     * Changes directory to a parent directory if {@code destinationDirectoryName} parameter is "..".
     *
     * @param destinationDirectoryName directory change to
     * @throws IllegalArgumentException if {@code File(destinationDirectoryName)} is a file, instead of directory
     * @throws FileNotExistException if {@code File(destinationDirectoryName)} does not exist
     * @throws NullPointerException if {@code destinationDirectoryName} is null
     */
    public void cd(String destinationDirectoryName) throws IllegalArgumentException, FileNotExistException {
        Objects.requireNonNull(destinationDirectoryName);

        checkDestinationDirectoryIsDirectory(destinationDirectoryName);

        changeDirectory(destinationDirectoryName);
    }

    /**
     * Set working directory to user home directory.
     */
    public void cd() {
        currentDirectory = homeDirectory;
    }

    private void changeDirectory(String destinationDirectoryName) throws FileNotExistException {
        if (destinationDirectoryName.equals("..")) {
            levelUpDirectory();
            return;
        }

        checkDestinationDirectoryExist(destinationDirectoryName);

        currentDirectory = destinationDirectoryName;
    }

    private void checkDestinationDirectoryExist(String destinationDirectoryName) throws FileNotExistException {
        final File destination = new File(destinationDirectoryName);

        if (!destination.exists()) {
            throw new FileNotExistException(destinationDirectoryName + " does not exist");
        }
    }

    private void levelUpDirectory() {
        final File currentPosition = new File(currentDirectory);

        if (currentPosition.getParent() != null) {
            currentDirectory = currentPosition.getParent();
        }
    }

    private void checkDestinationDirectoryIsDirectory(String destinationDirectoryName) throws IllegalArgumentException {
        final File fileToCheck = new File(destinationDirectoryName);

        if (fileToCheck.isFile()) {
            throw new IllegalArgumentException(destinationDirectoryName + " is not a directory");
        }
    }


    /**
     * @return current working directory
     */
    public String pwd() {
        return currentDirectory;
    }
}
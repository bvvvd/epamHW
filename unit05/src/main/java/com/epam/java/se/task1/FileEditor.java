package com.epam.java.se.task1;

import com.epam.java.se.task1.exceptions.DirectoryRemovingException;
import com.epam.java.se.task1.exceptions.FileNotExistException;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.Objects;

/**
 * Class provides primitive method kit for creating and removing files and directories in file system.
 *
 * @author Valeriy Burmistrov
 */
public class FileEditor {


    /**
     * Creates new empty file with specified name.
     *
     * @param fileToCreateName name of file to create
     * @throws FileAlreadyExistsException if file with specified name is already exist
     * @throws NullPointerException if arguments are null
     * @throws IOException
     */
    public void touch(String fileToCreateName) throws IOException {
        Objects.requireNonNull(fileToCreateName);

        final File fileToCreate = new File(fileToCreateName);

        if (!fileToCreate.createNewFile()) {
            throw new FileAlreadyExistsException(fileToCreateName + " is already exists");
        }
    }

    /**
     * Removes the file or directory by specified name.
     * If {@code new File(fileToRemoveName} is directory, directory must be empty to be deleted.
     *
     * @param fileToRemoveName name of file or directory to remove
     * @throws FileNotExistException if file with specified name does not exist
     * @throws DirectoryRemovingException if specified directory is not empty
     * @throws NullPointerException if arguments are null
     */
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

    /**
     * Creates new empty directory with specified name.
     *
     * @param directoryToCreateName name of directory to create
     * @throws FileAlreadyExistsException if specified directory is already exist
     * @throws IllegalArgumentException if specified directory's parent is not a directory,
     *                                  or if specified directory parents does not exits
     * @throws NullPointerException if arguments are null
     */
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

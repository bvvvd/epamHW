package com.epam.java.se;

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
}

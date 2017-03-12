package com.epam.java.se;

import java.io.File;

public class Nautilus {

    private final String currentDirectory;

    public Nautilus() {
        this.currentDirectory = System.getProperty("user.home");
    }

    public File[] ls(File file) {
        return file.listFiles();
    }
}
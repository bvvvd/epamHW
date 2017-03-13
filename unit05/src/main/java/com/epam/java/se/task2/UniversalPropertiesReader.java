package com.epam.java.se.task2;

import java.io.*;
import java.util.Objects;
import java.util.Properties;

public class UniversalPropertiesReader {

    private final String propertiesFileName;
    private Properties propertiesFile;

    public UniversalPropertiesReader(String propertiesFileName) throws NoSuchPropertiesFileException, IOException {
        Objects.requireNonNull(propertiesFileName);

        checkPropertiesFileExists(propertiesFileName);

        this.propertiesFileName = propertiesFileName;

        loadProperties(propertiesFileName);
    }

    private void loadProperties(String propertiesFileName) throws IOException {
        final Properties properties = new Properties();
        properties.load(new FileReader(".\\src\\resources\\task2resources_en.properties"));
        propertiesFile = properties;
    }

    private void checkPropertiesFileExists(String propertiesFileName) throws NoSuchPropertiesFileException {
        final File fileToCheckExistence = new File(propertiesFileName);

        if (fileToCheckExistence.exists()) {
            return;
        }

        throw new NoSuchPropertiesFileException(fileToCheckExistence + " does not exist");
    }

    public Properties getProperties() {
        return (Properties) propertiesFile.clone();
    }
}

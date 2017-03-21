package com.epam.java.se.task2;

import com.epam.java.se.task2.exceptions.NoSuchKeyInPropertiesFileException;
import com.epam.java.se.task2.exceptions.NoSuchPropertiesFileException;

import java.io.*;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class allows to read and get value from any properties file
 *
 * @author Valeriy Burmistrov
 */
public class UniversalPropertiesReader {
    private final String propertiesFileName;
    private volatile Properties properties;

    /**
     * Creates new properties reader for properties file with specified fileName
     *
     * @param propertiesFileName name of file to get properties
     * @throws NoSuchPropertiesFileException if file with given name does not exist
     * @throws IOException
     * @throws NullPointerException if {@code propertiesFileName} is null
     */
    public UniversalPropertiesReader(String propertiesFileName) throws NoSuchPropertiesFileException, IOException, InterruptedException {
        Objects.requireNonNull(propertiesFileName);

        checkPropertiesFileExists(propertiesFileName);

        this.propertiesFileName = propertiesFileName;

        loadProperties(propertiesFileName);
    }

    public UniversalPropertiesReader(FileReader fileReader) throws IOException {
        loadProperties(fileReader);
        propertiesFileName = null;
    }

    private void loadProperties(FileReader fileReader) throws IOException {
        final Properties properties = new Properties();
        properties.load(fileReader);
        this.properties = properties;
    }

    private void loadProperties(String propertiesFileName) throws IOException, InterruptedException {
        final Properties properties = new Properties();
        Lock lock = new ReentrantLock();
        FileReader reader = new FileReader(propertiesFileName);
        synchronized (reader) {
            properties.load(reader);
        }
        this.properties = properties;
    }

    private void checkPropertiesFileExists(String propertiesFileName) throws NoSuchPropertiesFileException {
        final File fileToCheckExistence = new File(propertiesFileName);

        if (fileToCheckExistence.exists()) {
            return;
        }

        throw new NoSuchPropertiesFileException(fileToCheckExistence + " does not exist");
    }

    public Properties getProperties() {
        return (Properties) properties.clone();
    }

    /**
     * Gets a value casted to {@code String} from properties by key
     *
     * @param key a key of value needed to get
     * @return value casted to {@code String} by key
     * @throws NoSuchKeyInPropertiesFileException if there is no such key in properties
     */
    public String getValue(String key) throws NoSuchKeyInPropertiesFileException {
        Objects.requireNonNull(key);

        if (!properties.containsKey(key)) {
            throw new NoSuchKeyInPropertiesFileException(propertiesFileName + " does not contain such key: " + key);
        }

        return (String) properties.get(key);
    }
}
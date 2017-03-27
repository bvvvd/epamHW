package com.epam.java.se.task2;

import com.epam.java.se.task2.exceptions.NoSuchKeyInPropertiesFileException;
import com.epam.java.se.task2.exceptions.NoSuchPropertiesFileException;

import java.io.*;
import java.util.*;

/**
 * Class allows to read and get values from any properties files.
 * Class uses HashMap to store properties key-value.
 *
 * @author Valeriy Burmistrov
 */
public class UniversalPropertiesReader {
    private final String fileName;
    private Map<String, String> propertiesMap = new HashMap<>();

    /**Creates new properties reader for properties file with specified name.
     *
     * @param fileName fileName - name of the file to get properties
     * @throws NoSuchPropertiesFileException if the file with given name does not exist
     * @throws IOException
     * @throws NullPointerException if {@code fileName} is null
     */
    public UniversalPropertiesReader(String fileName) throws IOException, NoSuchPropertiesFileException {
        Objects.requireNonNull(fileName);

        checkPropertiesFileExists(fileName);

        this.fileName = fileName;

        loadProperties();
    }

    private void loadProperties() throws FileNotFoundException {
        final BufferedReader reader = new BufferedReader(new FileReader(fileName));

        reader.lines().forEach((line) -> {
            final StringTokenizer tokenizer = new StringTokenizer(line, "=");
            propertiesMap.put(tokenizer.nextToken(), tokenizer.nextToken());
        });
    }

    private void checkPropertiesFileExists(String fileName) throws NoSuchPropertiesFileException {
        final File fileToCheckExistence = new File(fileName);

        if (!fileToCheckExistence.exists()) {
            throw new NoSuchPropertiesFileException(fileName + " does not exist");
        }
    }

    public Map<String, String> getProperties() {
        return Collections.unmodifiableMap(propertiesMap);
    }

    /**
     * Gets a value from properties by key
     *
     * @param key a key of value needed to get
     * @return value by key
     * @throws NoSuchKeyInPropertiesFileException if there is no such key in properties
     */
    public String getValue(String key) throws NoSuchKeyInPropertiesFileException {
        Objects.requireNonNull(key);

        if(!propertiesMap.containsKey(key)) {
            throw new NoSuchKeyInPropertiesFileException(fileName + "does not contain such key: " + key);
        }

        return propertiesMap.get(key);
    }
}

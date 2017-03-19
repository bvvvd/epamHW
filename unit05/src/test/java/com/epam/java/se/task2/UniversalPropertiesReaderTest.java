package com.epam.java.se.task2;

import com.epam.java.se.task2.exceptions.NoSuchKeyInPropertiesFileException;
import com.epam.java.se.task2.exceptions.NoSuchPropertiesFileException;
import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class UniversalPropertiesReaderTest {

    @Test
    public void testThatWeCanReadExistingPropertiesFile() throws IOException, NoSuchPropertiesFileException {
        final UniversalPropertiesReader propertiesReader =
                new UniversalPropertiesReader(".\\src\\resources\\task2resources_en.properties");

        final Properties properties = new Properties();
        properties.load(new FileReader(".\\src\\resources\\task2resources_en.properties"));

        assertThat(propertiesReader.getProperties().equals(properties), is(true));
    }

    @Test(expected = NoSuchPropertiesFileException.class)
    public void testThatTryingToReadNotExistingPropertiesFileThrowsNoSuchPropertiesFileException() throws IOException, NoSuchPropertiesFileException {
        final UniversalPropertiesReader propertiesReader =
                new UniversalPropertiesReader("notexistingfile.properties");
    }

    @Test(expected = NullPointerException.class)
    public void testThatTryingToReadPropertiesFileWithNullArgumentThrowsNPE() throws IOException, NoSuchPropertiesFileException {
        final UniversalPropertiesReader propertiesReader =
                new UniversalPropertiesReader(null);
    }

    @Test
    public void testThatWeCanGetValueWithExistingKey() throws IOException, NoSuchPropertiesFileException, NoSuchKeyInPropertiesFileException {
        final UniversalPropertiesReader propertiesReader =
                new UniversalPropertiesReader(".\\src\\resources\\task2resources_ru.properties");

        final Properties properties = new Properties();
        properties.load(new FileReader(".\\src\\resources\\task2resources_ru.properties"));
        final String expected = (String) properties.get("key1");

        assertThat(propertiesReader.getValue("key1").equals(expected), is(true));
    }

    @Test(expected = NoSuchKeyInPropertiesFileException.class)
    public void testThatTryingToGetValueWithNonExistingKeyThrowsNoSuchKeyInPropertiesFileException()
            throws IOException, NoSuchPropertiesFileException, NoSuchKeyInPropertiesFileException {
        final UniversalPropertiesReader propertiesReader =
                new UniversalPropertiesReader(".\\src\\resources\\task2resources_en.properties");

        propertiesReader.getValue("obviouslyNotExistingKey");
    }

    @Test(expected = NullPointerException.class)
    public void testThatGetValueWithNullArgumentKeyThrowsNPE()
            throws IOException, NoSuchPropertiesFileException, NoSuchKeyInPropertiesFileException {
        final UniversalPropertiesReader propertiesReader =
                new UniversalPropertiesReader(".\\src\\resources\\task2resources_en.properties");

        propertiesReader.getValue(null);
    }
}
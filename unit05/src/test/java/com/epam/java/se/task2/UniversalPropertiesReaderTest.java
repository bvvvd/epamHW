package com.epam.java.se.task2;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.ResourceBundle;

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
}
package com.epam.java.se;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class UniversalPropertiesReaderTest {

    private UniversalPropertiesReader propertiesReader;

    @Before
    public void init() throws FileNotFoundException, NoSuchPropertiesFileException {
        this.propertiesReader = new UniversalPropertiesReader(".\\src\\resources\\task2resources_en.properties");
    }

    @Test
    public void testThatWeCanCreateNewPropertiesReader() {
        assertThat(propertiesReader, is(notNullValue()));
    }

    @Test(expected = NullPointerException.class)
    public void testCreatingPropertiesReaderWithNullArgumentThrowsNPE() throws FileNotFoundException, NoSuchPropertiesFileException {
        final UniversalPropertiesReader propertiesReader = new UniversalPropertiesReader(null);
    }

    @Test(expected = NoSuchPropertiesFileException.class)
    public void testCreatingPropertiesReaderWithNotExistingPropertiesFileAsArgumentThrowsIllegalArgumentException() throws FileNotFoundException, NoSuchPropertiesFileException {
        final UniversalPropertiesReader propertiesReader = new UniversalPropertiesReader("notexisting.file");
    }

    @Test
    public void testThatPropertiesReaderCreatedWithNotEmptyPropertiesFileIsNotEmpty() {
        assertThat(propertiesReader.getProperties().isEmpty(), is(false));
    }

    @Test
    public void testThatPropertiesMapContainsAllKeyValuePairs() throws FileNotFoundException {
        final BufferedReader reader =
                new BufferedReader(new FileReader(".\\src\\resources\\task2resources_en.properties"));

        final Map<String, String> expectedMap= new HashMap<>();
        reader.lines().forEach((line) -> {
            final StringTokenizer tokenizer = new StringTokenizer(line, "=");
            expectedMap.put(tokenizer.nextToken(), tokenizer.nextToken());
        });

        assertThat(propertiesReader.getProperties().equals(expectedMap), is(true));
    }

    @Test
    public void testThatWeCanGetValueWithExistingKey() throws NoSuchKeyInPropertiesFileException {
        assertThat(propertiesReader.getValue("key1").equals("value1"), is(true));
    }

    @Test(expected = NullPointerException.class)
    public void testThatGetValueWithNullArgumentThrowsNPE() throws NoSuchKeyInPropertiesFileException {
        propertiesReader.getValue(null);
    }

    @Test(expected = NoSuchKeyInPropertiesFileException.class)
    public void testThatGetValueWithNonExistingKeyThrowsNoSuchKeyInPropertiesFileException() throws NoSuchKeyInPropertiesFileException {
        propertiesReader.getValue("notexistingkey");
    }
}

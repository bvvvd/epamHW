package com.epam.java.se.task2;

import com.epam.java.se.task2.exceptions.NoSuchKeyInPropertiesFileException;
import com.epam.java.se.task2.exceptions.NoSuchPropertiesFileException;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class UniversalPropertiesReaderTest {

    private FileReader fileReader;

    @Before
    public void init() throws FileNotFoundException {
        this.fileReader = new FileReader(".\\src\\resources\\task2resources_en.properties");
    }

    @Test
    public void testThatWeCanReadExistingPropertiesFile() throws IOException, NoSuchPropertiesFileException {
        final UniversalPropertiesReader propertiesReader =
                new UniversalPropertiesReader(fileReader);

        final Properties properties = new Properties();
        properties.load(new FileReader(".\\src\\resources\\task2resources_en.properties"));

        assertThat(propertiesReader.getProperties().equals(properties), is(true));
    }

    @Test(expected = NoSuchPropertiesFileException.class)
    public void testThatTryingToReadNotExistingPropertiesFileThrowsNoSuchPropertiesFileException() throws IOException, NoSuchPropertiesFileException, InterruptedException {
        final UniversalPropertiesReader propertiesReader =
                new UniversalPropertiesReader("notexistingfile.properties");
    }

    @Test
    public void testThatWeCanGetValueWithExistingKey() throws IOException, NoSuchPropertiesFileException, NoSuchKeyInPropertiesFileException, InterruptedException {
        final UniversalPropertiesReader propertiesReader =
                new UniversalPropertiesReader(".\\src\\resources\\task2resources_ru.properties");

        final Properties properties = new Properties();
        properties.load(new FileReader(".\\src\\resources\\task2resources_ru.properties"));
        final String expected = (String) properties.get("key1");

        assertThat(propertiesReader.getValue("key1").equals(expected), is(true));
    }

    @Test(expected = NoSuchKeyInPropertiesFileException.class)
    public void testThatTryingToGetValueWithNonExistingKeyThrowsNoSuchKeyInPropertiesFileException()
            throws IOException, NoSuchPropertiesFileException, NoSuchKeyInPropertiesFileException, InterruptedException {
        final UniversalPropertiesReader propertiesReader =
                new UniversalPropertiesReader(".\\src\\resources\\task2resources_en.properties");

        propertiesReader.getValue("obviouslyNotExistingKey");
    }

    @Test(expected = NullPointerException.class)
    public void testThatGetValueWithNullArgumentKeyThrowsNPE()
            throws IOException, NoSuchPropertiesFileException, NoSuchKeyInPropertiesFileException, InterruptedException {
        final UniversalPropertiesReader propertiesReader =
                new UniversalPropertiesReader(".\\src\\resources\\task2resources_en.properties");

        propertiesReader.getValue(null);
    }

    @Test
    public void testMultipleThreadsTryingToReadFile() throws InterruptedException, IOException {
        Runnable runnable = () -> {
            try {
                new UniversalPropertiesReader(".\\src\\resources\\task2resources_en.properties");
            } catch (IOException | NoSuchPropertiesFileException | InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        Thread t3 = new Thread(runnable);
        Thread t4 = new Thread(runnable);
        Thread t5 = new Thread(runnable);
        Thread t6 = new Thread(runnable);
        Thread t7 = new Thread(runnable);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.join();
        t6.join();
        t7.join();
    }
}
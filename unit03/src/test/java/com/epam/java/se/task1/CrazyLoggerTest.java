package com.epam.java.se.task1;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CrazyLoggerTest {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY : HH-mm");
    private final String lineSeparator = System.getProperty("line.separator");

    @Test
    public void testThatWeCanAddLogsInCrazyLogger() {
        final CrazyLogger logger = new CrazyLogger();
        logger.log("first log");

        final LocalDateTime logDateAndTime = LocalDateTime.now();
        final String formattedLogDateAndTime = logDateAndTime.format(formatter);
        final String expected = formattedLogDateAndTime.concat(" - first log;").concat(lineSeparator);

        assertThat(logger.toString(), is(expected));
    }

    @Test
    public void testThatWeCanAddLogsInNotEmptyCrazyLogger() {
        final CrazyLogger logger = new CrazyLogger();
        logger.log("first log");

        final LocalDateTime firstLogDateAndTime = LocalDateTime.now();
        final String formattedFirstLogDateAndTime = firstLogDateAndTime.format(formatter);
        final String expectedFirstLog = formattedFirstLogDateAndTime.concat(" - first log;").concat(lineSeparator);

        logger.log("second log");

        final LocalDateTime secondLogDateAndTime = LocalDateTime.now();
        final String formattedSecondLogDateAndTime = secondLogDateAndTime.format(formatter);
        final String expected = expectedFirstLog.concat(formattedSecondLogDateAndTime).
                concat(" - second log;").concat(lineSeparator);

        assertThat(logger.toString(), is(expected));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThatLoggingNullStringMessageThrowsIllegalArgumentException() {
        final CrazyLogger logger = new CrazyLogger();
        logger.log(null);
    }

    @Test
    public void testThatAddingEmptyMessageDoesNotChangeLog() {
        final CrazyLogger logger = new CrazyLogger();
        logger.log("first log");
        logger.log("");

        final LocalDateTime logDateAndTime = LocalDateTime.now();
        final String formattedLogDateAndTime = logDateAndTime.format(formatter);
        final String expected = formattedLogDateAndTime.concat(" - first log;").concat(lineSeparator);

        assertThat(logger.toString(), is(expected));
    }

    @Test
    public void testAddingStringContainsLineSeparator() {
        final CrazyLogger logger = new CrazyLogger();
        logger.log("first" + lineSeparator + " log");

        final LocalDateTime logDateAndTime = LocalDateTime.now();
        final String formattedLogDateAndTime = logDateAndTime.format(formatter);
        final String expected = formattedLogDateAndTime.concat(" - first log;").concat(lineSeparator);

        assertThat(logger.toString(), is(expected));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThatExtractNullLogThrowsIllegalArgumentException() {
        final CrazyLogger logger = new CrazyLogger();
        logger.extractLogsByString(null);
    }

    @Test
    public void testThatExtractingEmptyStringReturnsEntireLog() {
        final CrazyLogger logger = new CrazyLogger();
        logger.log("first log");
        logger.log("second log");

        final String expected = logger.toString();
        assertThat(logger.extractLogsByString(""), is(expected));
    }

    @Test
    public void testThatExtractingStringContainingLineSeparatorReturnsEntireLog() {
        final CrazyLogger logger = new CrazyLogger();
        logger.log("first log");
        logger.log("second log");

        final String expected = logger.toString();
        assertThat(logger.extractLogsByString(lineSeparator), is(expected));
    }

    @Test
    public void testExtractingStringNotContainingInLogReturnsEmptyString() {
        final CrazyLogger logger = new CrazyLogger();
        logger.log("first log");
        logger.log("second log");
        logger.log("third log");
        logger.log("fourth log");

        assertThat(logger.extractLogsByString("i will return empty string"), is(""));
    }

    @Test
    public void testExtractSingleLogByMessageText() {
        final CrazyLogger logger = new CrazyLogger();
        logger.log("first log");
        logger.log("second log");
        logger.log("third log");
        logger.log("fourth log");

        final String extractedLogs = logger.extractLogsByString("third log");

        assertThat(extractedLogs.contains("third log"), is(true));
    }

    @Test
    public void testExtractMultipleLogsByString() {
        final CrazyLogger logger = new CrazyLogger();
        logger.log("first log");
        logger.log("not a first log");

        final String extractedLogs = logger.extractLogsByString("first log");

        assertThat(extractedLogs.equals(logger.toString()), is(true));
    }

}

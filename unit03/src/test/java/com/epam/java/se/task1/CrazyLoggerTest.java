package com.epam.java.se.task1;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class CrazyLoggerTest {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY : HH-mm");

    @Test
    public void testThatWeCanAddLogsInCrazyLogger() throws Exception {
        final CrazyLogger logger = new CrazyLogger();
        logger.log("first log");

        final LocalDateTime logDateAndTime = LocalDateTime.now();
        final String formattedLogDateAndTime = logDateAndTime.format(formatter);
        final String expected = formattedLogDateAndTime.concat(" - first log;|");

        assertThat(logger.entireLogToString(), is(expected));
    }

    @Test
    public void testThatWeCanAddLogsInNotEmptyCrazyLogger() throws Exception {
        final CrazyLogger logger = new CrazyLogger();
        logger.log("first log");

        final LocalDateTime firstLogDateAndTime = LocalDateTime.now();
        final String formattedFirstLogDateAndTime = firstLogDateAndTime.format(formatter);
        final String expectedFirstLog = formattedFirstLogDateAndTime.concat(" - first log;|");

        logger.log("second log");

        final LocalDateTime secondLogDateAndTime = LocalDateTime.now();
        final String formattedSecondLogDateAndTime = secondLogDateAndTime.format(formatter);
        final String expected = expectedFirstLog.concat(formattedSecondLogDateAndTime).concat(" - second log;|");

        assertThat(logger.entireLogToString(), is(expected));

    }
}

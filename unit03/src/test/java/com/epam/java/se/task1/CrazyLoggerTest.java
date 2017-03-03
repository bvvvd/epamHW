package com.epam.java.se.task1;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by chris on 03.03.2017.
 */
public class CrazyLoggerTest {

    @Test
    public void testThatWeCanAddLogsInCrazyLogger() throws Exception {
        final CrazyLogger logger = new CrazyLogger();

        LocalDateTime localDateTime = LocalDateTime.of(2017, 3, 3, 17, 30);
        logger.log(localDateTime, "first log");

        final String expected = "03-03-2017 : 17-30 - first log;|";

        assertThat(expected, is(logger.entireLogToString()));
    }
}

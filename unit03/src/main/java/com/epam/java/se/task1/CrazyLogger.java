package com.epam.java.se.task1;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by chris on 03.03.2017.
 */
public class CrazyLogger {
    private StringBuilder log = new StringBuilder();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY : HH-mm");

    public String entireLogToString() {
        return log.toString();
    }

    public void log(LocalDateTime logTime, String message) {
        final String dateAndTime = logTime.format(formatter).toString();
        log.append(dateAndTime).append(" - ").append(message).append(";|");
    }
}

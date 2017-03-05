package com.epam.java.se.task1;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CrazyLogger {
    private final StringBuilder logWarehouse = new StringBuilder();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY : HH-mm");

    public String entireLogToString() {
        return logWarehouse.toString();
    }

    public void log(String message) throws InterruptedException {
        final LocalDateTime logDateAndTime = LocalDateTime.now();
        final String formattedLogDateAndTime = logDateAndTime.format(formatter).toString();
        logWarehouse.append(formattedLogDateAndTime).append(" - ").append(message).append(";|");
    }
}

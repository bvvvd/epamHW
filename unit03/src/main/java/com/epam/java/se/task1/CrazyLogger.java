package com.epam.java.se.task1;

import javax.annotation.Nonnull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CrazyLogger {
    private final StringBuilder logWarehouse = new StringBuilder();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY : HH-mm");
    private final String lineSeparator = System.getProperty("line.separator");

    public void log(@Nonnull String message) {
        if (message.isEmpty()) {
            return;
        }

        final String editedMessage = ensureMessageDoesNotContainLineSeparators(message);

        final String formattedLogDateAndTime = formatLogDateAndTime();

        logWarehouse.append(formattedLogDateAndTime)
                .append(" - ")
                .append(editedMessage)
                .append(";")
                .append(lineSeparator);
    }

    private String ensureMessageDoesNotContainLineSeparators(String message) {
       return message.replaceAll(lineSeparator, " ").replaceAll("  ", " ");
    }

    private String formatLogDateAndTime() {
        final LocalDateTime logDateAndTime = LocalDateTime.now();
        return logDateAndTime.format(formatter);
    }

    @Override
    public String toString() {
        return logWarehouse.toString();
    }
}

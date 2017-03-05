package com.epam.java.se.task1;

import javax.annotation.Nonnull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.StringTokenizer;

/**
 * Stores logs in format: date and time - message
 *
 * @author Valeriy Burmistrov
 */
public class CrazyLogger {
    private final StringBuilder logWarehouse = new StringBuilder();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-YYYY : HH-mm");
    private final String lineSeparator = System.getProperty("line.separator");

    /**
     * Adds the specified message to the log. Uses {@code LocalDateAndTime.now()} to calculate date and time of log
     *
     * @param message string of message needed to add
     * @throws IllegalArgumentException if {@code message} is null
     */
    public void log(@Nonnull String message) {
        if (message.isEmpty()) {
            return;
        }

        final String editedMessage = ensureMessageDoesNotContainLineSeparators(message);

        final String formattedLogDateAndTime = formatLogDateAndTime();

        addMessageToLog(editedMessage, formattedLogDateAndTime);
    }

    /**
     * Extract logs that contain the specified string
     *
     * @param stringToFind string needed to contain in log
     * @return string that contains all necessary logs or an epty string if these are not found
     * @throws IllegalArgumentException if {@code stringToFind} is null
     */
    public String extractLogsByString(@Nonnull String stringToFind) {
        if (stringToFind.isEmpty() || stringToFind.equals(lineSeparator)) {
            return toString();
        }

        return createResultOfExtractionString(stringToFind);
    }

    private void addMessageToLog(String editedMessage, String formattedLogDateAndTime) {
        logWarehouse.append(formattedLogDateAndTime)
                .append(" - ")
                .append(editedMessage)
                .append(";")
                .append(lineSeparator);
    }

    private String createResultOfExtractionString(@Nonnull String stringToExtract) {
        final StringBuilder result = new StringBuilder();

        StringTokenizer logWarehouseTokenizer = new StringTokenizer(logWarehouse.toString(), lineSeparator);

        while (logWarehouseTokenizer.hasMoreTokens()) {
            final String token = logWarehouseTokenizer.nextToken();
            if (token.contains(stringToExtract)) {
                result.append(token).append(lineSeparator);
            }
        }

        return result.toString();
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

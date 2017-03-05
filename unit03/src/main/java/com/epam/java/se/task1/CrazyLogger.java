package com.epam.java.se.task1;

import javax.annotation.Nonnull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.StringTokenizer;

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

    public String extractLogsByString(@Nonnull String stringToExtract) {
        if (stringToExtract.isEmpty() || stringToExtract.equals(lineSeparator)) {
            return toString();
        }

        if (!logWarehouse.toString().contains(stringToExtract)) {
            return "";
        }

        return createResultOfExtractionString(stringToExtract);
    }

    private String createResultOfExtractionString(@Nonnull String stringToExtract) {
        final StringBuilder result = new StringBuilder();

        StringTokenizer tokenizer = new StringTokenizer(logWarehouse.toString(), lineSeparator);

        while (tokenizer.hasMoreTokens()) {
            final String token = tokenizer.nextToken();
            if (token.contains(stringToExtract)) {
                result.append(token);
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

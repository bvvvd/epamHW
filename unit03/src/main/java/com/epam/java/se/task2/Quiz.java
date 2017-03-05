package com.epam.java.se.task2;

import javax.annotation.Nonnull;
import java.util.Locale;
import java.util.ResourceBundle;

public class Quiz {
    private static final ResourceBundle availableLanguages = ResourceBundle.getBundle("languages");
    private final Locale locale;
    private final String lineSeparator = System.getProperty("line.separator");

    public Quiz(@Nonnull String language) {
        checkLanguageAvailability(language);

        this.locale = new Locale(language);
    }

    public Locale getLocale() {
        return new Locale(locale.getLanguage());
    }

    public static void printAvailableLanguages() {
        availableLanguages.keySet().forEach(System.out::println);
    }

    private void checkLanguageAvailability(String language) {
        if (availableLanguages.keySet().contains(language)) {
            return;
        }

        throw new IllegalArgumentException("Illegal language, language can be: "
                .concat(getAvailableLanguageAsString()));
    }

    private String getAvailableLanguageAsString() {
        final StringBuilder result = new StringBuilder();

        availableLanguages.keySet().forEach((languageKey) -> result.append(languageKey).append(" "));

        return result.toString();
    }
}

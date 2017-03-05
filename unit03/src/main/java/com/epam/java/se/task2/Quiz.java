package com.epam.java.se.task2;

import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public class Quiz {
    private static final ResourceBundle availableLanguages = ResourceBundle.getBundle("languages");
    private final Locale locale;
    private final String lineSeparator = System.getProperty("line.separator");
    private ResourceBundle questions;
    private ResourceBundle answers;

    public Quiz(String language) {
        Objects.requireNonNull(language);
        checkLanguageAvailability(language);

        locale = new Locale(language);
        questions = ResourceBundle.getBundle("questions", new Locale(language));
        answers = ResourceBundle.getBundle("answers", new Locale(language));
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

    public ResourceBundle getQuestions() {
        return questions;
    }

    public ResourceBundle getAnswers() {
        return answers;
    }
}

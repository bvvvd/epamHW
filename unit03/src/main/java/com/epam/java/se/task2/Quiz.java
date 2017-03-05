package com.epam.java.se.task2;

import java.util.Locale;
import java.util.MissingResourceException;
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

    public String getQuestion(int numberOfQuestion) {
        try {
            final String questionKey = "question".concat(String.valueOf(numberOfQuestion));
            return questions.getString(questionKey);
        } catch (MissingResourceException e) {
            final int numberOfQuestions = questions.keySet().size();
            throw new IllegalArgumentException(String.format(
                    "Wrong number of question. Number must be from 1 to %s", numberOfQuestions));
        }
    }

    public String getAnswer(int numberOfAnswer) {
        try {
            final String answerKey = "answer".concat(String.valueOf(numberOfAnswer));
            return answers.getString(answerKey);
        } catch (MissingResourceException e) {
            final int numberOfAnswers = questions.keySet().size();
            throw new IllegalArgumentException(String.format(
                    "Wrong number of answer. Number must be from 1 to %s", numberOfAnswers));
        }
    }
}

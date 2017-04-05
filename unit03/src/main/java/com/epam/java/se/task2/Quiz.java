package com.epam.java.se.task2;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * This class provide simple quiz. Supports russian and english interface and data.
 *
 * @author Valeriy Burmistrov
 */
public class Quiz {
    public static final ResourceBundle availableLanguages = ResourceBundle.getBundle("languages");
    private final Locale locale;
    private ResourceBundle questions;
    private ResourceBundle answers;

    /**
     * Create new {@code Quiz} with specified language
     *
     * @param language language of quiz' interface
     */
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

    public void printQuestionOffer() {
        printOffer(locale);
    }

    private void printOffer(Locale locale) {
        if (locale.equals(new Locale("en"))) {
            System.out.println("Choose number of question, if you want to end game - type stop");
        } else {
            System.out.println("Введите номер вопроса, если вы хотите закончить игру - введите stop");
        }
    }

    public void printTypingRequirements() {
        printRequirements(locale);
    }

    private void printRequirements(Locale locale) {
        if (locale.equals(new Locale("en"))) {
            System.out.println("Number of question must be a integer number");
        }else {
            System.out.println("Номер вопрса может состоять только из целого числа");
        }
    }
}

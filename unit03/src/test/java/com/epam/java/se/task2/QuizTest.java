package com.epam.java.se.task2;

import org.junit.Test;

import java.util.Locale;
import java.util.ResourceBundle;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class QuizTest {

    @Test
    public void testCreateQuizEnglishLanguage() {
        final Quiz quiz = new Quiz("en");

        final Locale expectedLocale = new Locale("en");

        assertThat(quiz.getLocale(), is(expectedLocale));
    }

    @Test
    public void testCreateQuizRussianLanguage() {
        final Quiz quiz = new Quiz("ru");

        final Locale expectedLocale = new Locale("ru");
        assertThat(quiz.getLocale(), is(expectedLocale));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateQuizUnsupportedLanguageThrowsIllegalArgumentException() {
        final Quiz quiz = new Quiz("fr");
    }

    @Test(expected = NullPointerException.class)
    public void testCreateQuizNullArgumentLanguageThrowsNullPointerException() {
        final Quiz quiz = new Quiz(null);
    }

    @Test
    public void testThatQuizConstructingWithCorrectResourcesBundlesForEnglish() {
        final Quiz quiz = new Quiz("en");
        final ResourceBundle realQuestions = quiz.getQuestions();
        final ResourceBundle realAnswers = quiz.getAnswers();

        final ResourceBundle expectedQuestions = ResourceBundle.getBundle("questions", new Locale("en"));
        final ResourceBundle expectedAnswers = ResourceBundle.getBundle("answers", new Locale("en"));

        assertThat(realQuestions.equals(expectedQuestions), is(true));
        assertThat(realAnswers.equals(expectedAnswers), is(true));
    }

    @Test
    public void testThatQuizConstructingWithCorrectResourcesBundlesForRussian() {
        final Quiz quiz = new Quiz("ru");
        final ResourceBundle realQuestions = quiz.getQuestions();
        final ResourceBundle realAnswers = quiz.getAnswers();

        final ResourceBundle expectedQuestions = ResourceBundle.getBundle("questions", new Locale("ru"));
        final ResourceBundle expectedAnswers = ResourceBundle.getBundle("answers", new Locale("ru"));

        assertThat(realQuestions.equals(expectedQuestions), is(true));
        assertThat(realAnswers.equals(expectedAnswers), is(true));
    }
}

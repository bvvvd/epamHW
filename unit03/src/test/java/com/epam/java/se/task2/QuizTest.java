package com.epam.java.se.task2;

import org.junit.Test;

import java.util.Locale;

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

    @Test(expected = IllegalArgumentException.class)
    public void testCreateQuizNullArgumentLanguageThrowsIllegalArgumentException() {
        final Quiz quiz = new Quiz(null);
    }
}

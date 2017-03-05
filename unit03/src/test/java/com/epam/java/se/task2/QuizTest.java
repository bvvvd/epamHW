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
    public void testThatQuizConstructsWithCorrectResourcesBundlesForEnglish() {
        final Quiz quiz = new Quiz("en");
        final ResourceBundle realQuestions = quiz.getQuestions();
        final ResourceBundle realAnswers = quiz.getAnswers();

        final ResourceBundle expectedQuestions = ResourceBundle.getBundle("questions", new Locale("en"));
        final ResourceBundle expectedAnswers = ResourceBundle.getBundle("answers", new Locale("en"));

        assertThat(realQuestions.equals(expectedQuestions), is(true));
        assertThat(realAnswers.equals(expectedAnswers), is(true));
    }

    @Test
    public void testThatQuizConstructsWithCorrectResourcesBundlesForRussian() {
        final Quiz quiz = new Quiz("ru");
        final ResourceBundle realQuestions = quiz.getQuestions();
        final ResourceBundle realAnswers = quiz.getAnswers();

        final ResourceBundle expectedQuestions = ResourceBundle.getBundle("questions", new Locale("ru"));
        final ResourceBundle expectedAnswers = ResourceBundle.getBundle("answers", new Locale("ru"));

        assertThat(realQuestions.equals(expectedQuestions), is(true));
        assertThat(realAnswers.equals(expectedAnswers), is(true));
    }

    @Test
    public void testThatWeCanGetEnglishQuestionByNumber() {
        final Quiz quiz = new Quiz("en");

        final String realQuestionNumber5 = quiz.getQuestion(5);
        final String expectedQuestionNumber5 = ResourceBundle.
                getBundle("questions", new Locale("en"))
                .getString("question5");
        assertThat(realQuestionNumber5.equals(expectedQuestionNumber5), is(true));
    }

    @Test
    public void testThatWeCanGetRussianQuestionByNumber() {
        final Quiz quiz = new Quiz("ru");

        final String realQuestionNumber5 = quiz.getQuestion(5);
        final String expectedQuestionNumber5 = ResourceBundle.
                getBundle("questions", new Locale("ru"))
                .getString("question5");

        assertThat(realQuestionNumber5.equals(expectedQuestionNumber5), is(true));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThatGettingNotExistingEnglishQuestionThrowsIllegalArgumentException() {
        final Quiz quiz = new Quiz("ru");

        quiz.getQuestion(12);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testThatGettingNotExistingRussianQuestionThrowsIllegalArgumentException() {
        final Quiz quiz = new Quiz("ru");

        quiz.getQuestion(0);
    }

    @Test
    public void testThatWeCanGetRussianAnswerForQuestionByNumber() {
        final Quiz quiz = new Quiz("ru");

        final String realAnswerNumber5 = quiz.getAnswer(5);
        final String expectedAnswerNumber5 = ResourceBundle.
                getBundle("answers", new Locale("ru"))
                .getString("answer5");

        assertThat(realAnswerNumber5.equals(expectedAnswerNumber5), is(true));
    }

    @Test
    public void testThatWeCanGetEnglishAnswerForQuestionByNumber() {
        final Quiz quiz = new Quiz("en");

        final String realAnswerNumber5 = quiz.getAnswer(5);
        final String expectedAnswerNumber5 = ResourceBundle.
                getBundle("answers", new Locale("en"))
                .getString("answer5");

        assertThat(realAnswerNumber5.equals(expectedAnswerNumber5), is(true));
    }


    @Test(expected = IllegalArgumentException.class)
    public void testThatGettingNotExistingRussianAnswerThrowsIllegalArgumentException() {
        final Quiz quiz = new Quiz("ru");

        quiz.getAnswer(0);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testThatGettingNotExistingEnglishAnswerThrowsIllegalArgumentException() {
        final Quiz quiz = new Quiz("en");

        quiz.getAnswer(15);
    }
}

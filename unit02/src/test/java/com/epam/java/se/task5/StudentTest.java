package com.epam.java.se.task5;

import org.junit.Test;

import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by chris on 27.02.2017.
 */
public class StudentTest {

    @Test
    public void testAddDiscipline() throws Exception {
        final Student student = new Student("Jason", "Richardson", Gender.MALE, 25);

        student.addDiscipline(Discipline.MATH, new Rating(3.4));
        student.addDiscipline(Discipline.ENGLISH, new Rating(5));
        student.addDiscipline(Discipline.MUSIC, new Rating(10));

        Map<Discipline, Rating> disciplineMap = student.getDisciplineMap();

        assertThat(disciplineMap.get(Discipline.MATH).getRating(), is(3.4));
        assertThat(disciplineMap.get(Discipline.ENGLISH).getRating(), is(5));
        assertThat(disciplineMap.get(Discipline.MUSIC).getRating(), is(10));

    }

    @Test
    public void testAddDisciplineWrongRatingTypeForDisciplineThrowsIllegalArgumentException() throws Exception {

        final Student student = new Student("Jason", "Richardson", Gender.MALE, 25);

        try {
            student.addDiscipline(Discipline.MUSIC, new Rating(15.5));
        } catch (IllegalArgumentException e) {

        }

        try {
            student.addDiscipline(Discipline.MATH, new Rating(15));
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void testAddDisciplineWithNullArgumentsThrowsIllegalArgumentException() throws Exception {
        final Student student = new Student("Steven", "Hawking", Gender.MALE, 70);

        try {
            student.addDiscipline(null, null);
        } catch (IllegalArgumentException e) {

        }
    }
}

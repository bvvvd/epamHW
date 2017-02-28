package com.epam.java.se.task5;

import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by chris on 28.02.2017.
 */
public class StudyGroupControllerTest {

    @Test
    public void testThatWeCanFormGroupOfLoaferStudents() throws Exception {
        final Student loafer1 = new Student("Henry", "Ford", Gender.MALE, 70);
        final Student loafer2 = new Student("Janet", "Brown", Gender.FEMALE, 15);
        final Student notLoafer3 = new Student("James", "May", Gender.MALE, 50);

        notLoafer3.addDiscipline(Discipline.MUSIC, new Rating(8));

        ArrayList<Student> listOfStudents = new ArrayList<>();

        listOfStudents.add(loafer1);
        listOfStudents.add(loafer2);
        listOfStudents.add(notLoafer3);

        ArrayList<Student> listOfLoafers = StudyGroupController.formGroupOfLoafer(listOfStudents);

        assertThat(listOfLoafers.contains(loafer1), is(true));
        assertThat(listOfLoafers.contains(loafer2), is(true));
        assertThat(listOfLoafers.contains(notLoafer3), is(false));
    }
}

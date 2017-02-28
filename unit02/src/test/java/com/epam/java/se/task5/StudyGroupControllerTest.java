package com.epam.java.se.task5;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Set;

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

    @Test
    public void testThatWeCanFormGroupOfStudentsBySpecifiedDiscipline() {
        final Student loafer1 = new Student("Henry", "Ford", Gender.MALE, 70);
        final Student loafer2 = new Student("Janet", "Brown", Gender.FEMALE, 15);
        final Student notLoafer3 = new Student("James", "May", Gender.MALE, 50);

        notLoafer3.addDiscipline(Discipline.MUSIC, new Rating(8));

        ArrayList<Student> listOfStudents = new ArrayList<>();

        listOfStudents.add(loafer1);
        listOfStudents.add(loafer2);
        listOfStudents.add(notLoafer3);

        ArrayList<Student> listOfLoafers = StudyGroupController.formGroupByDiscipline(listOfStudents, Discipline.MUSIC);

        assertThat(listOfLoafers.contains(loafer1), is(false));
        assertThat(listOfLoafers.contains(loafer2), is(false));
        assertThat(listOfLoafers.contains(notLoafer3), is(true));
    }

    @Test
    public void testThatWeCanGetAllGroupsOfSpecifiedStudent() throws Exception {
        final Student student = new Student("Jason", "Richardson", Gender.MALE, 25);

        student.addDiscipline(Discipline.MATH, new Rating(3.4));
        student.addDiscipline(Discipline.ENGLISH, new Rating(5));
        student.addDiscipline(Discipline.MUSIC, new Rating(10));

        Set<Discipline> disciplinesOfStudent = StudyGroupController.getStudentDisciplineSet(student);

        assertThat(disciplinesOfStudent.contains(Discipline.MATH), is(true));
        assertThat(disciplinesOfStudent.contains(Discipline.MUSIC), is(true));
        assertThat(disciplinesOfStudent.contains(Discipline.ENGLISH), is(true));
        assertThat(disciplinesOfStudent.contains(Discipline.COMPUTER_SCIENCE), is(false));

    }

    @Test
    public void testThatWeCanGetDisciplinesWithBetterPerformanceThanSpecified() throws Exception {
        final Student student = new Student("Jason", "Richardson", Gender.MALE, 25);

        student.addDiscipline(Discipline.MUSIC, new Rating(10));
        student.addDiscipline(Discipline.ENGLISH, new Rating(5));
        student.addDiscipline(Discipline.MATH, new Rating(3.4));

        Set<Discipline> bestStudentDisciplines = StudyGroupController.
                getDisciplinesByBetterPerformance(student, new Rating(5));

        assertThat(bestStudentDisciplines.contains(Discipline.MATH), is(false));
        assertThat(bestStudentDisciplines.contains(Discipline.MUSIC), is(true));
        assertThat(bestStudentDisciplines.contains(Discipline.ENGLISH), is(false));
    }
}

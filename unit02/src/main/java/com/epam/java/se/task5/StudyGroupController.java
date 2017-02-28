package com.epam.java.se.task5;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by chris on 28.02.2017.
 */
public class StudyGroupController {


    public static ArrayList<Student> formGroupOfLoafer(ArrayList<Student> listOfStudents) {
        final ArrayList<Student> listOfLoafers = new ArrayList<>();

        for (Student student : listOfStudents) {
            if (student.getDisciplineMap().isEmpty()) {
                listOfLoafers.add(student);
            }
        }

        return listOfLoafers;
    }

    public static ArrayList<Student> formGroupByDiscipline(ArrayList<Student> listOfStudents, Discipline discipline) {
        final ArrayList<Student> disciplineGroup = new ArrayList<>();

        for (Student student : listOfStudents) {
            if (student.getDisciplineMap().containsKey(discipline)) {
                disciplineGroup.add(student);
            }
        }

        return disciplineGroup;
    }

    public static Set<Discipline> getStudentDisciplineSet(Student student) {

        final Map<Discipline, Rating> studentDisciplinesMap = student.getDisciplineMap();

        final Set<Discipline> disciplines = studentDisciplinesMap.keySet();

        return disciplines;

    }

    public static Set<Discipline> getDisciplinesByBetterPerformance(Student student, Rating rating) {
        Set<Discipline> disciplineSet = new HashSet<>();

        for (Map.Entry<Discipline, Rating> entry: student.getDisciplineMap().entrySet()) {

            if (entry.getValue().compareTo(rating) > 0) {
                disciplineSet.add(entry.getKey());
            }
        }

        return disciplineSet;
    }
}

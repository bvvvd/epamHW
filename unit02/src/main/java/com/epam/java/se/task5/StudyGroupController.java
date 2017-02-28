package com.epam.java.se.task5;

import java.util.ArrayList;

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
}

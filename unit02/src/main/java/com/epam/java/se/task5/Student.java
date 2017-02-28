package com.epam.java.se.task5;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.*;

/**
 * Created by chris on 28.02.2017.
 */
public class Student {

    private final String name;
    private final String subName;
    private final Gender gender;
    private final int age;
    private Map<Discipline, Rating> disciplineMap;

    public Student(@Nonnull String name, @Nonnull String subName, @Nonnull Gender gender, @Nonnegative int age) {
        this.name = name;
        this.subName = name;
        this.gender = gender;
        this.age = age;
        disciplineMap = new HashMap<>();
    }

    public void addDiscipline(@Nonnull Discipline discipline,@Nonnull Rating rating) {
        checkRatingValidity(discipline, rating);

        disciplineMap.put(discipline, rating);
    }

    private void checkRatingValidity(Discipline discipline, Rating rating) {

        switch (discipline) {
            case MUSIC:
            case ENGLISH:
                if (!(rating.getRating() instanceof Integer)) {
                    throw new IllegalArgumentException("Rating for ".concat(discipline.toString())
                            .concat(" must be integer number"));
                }
                break;

            case COMPUTER_SCIENCE:
            case MATH:
            case PHYSICS:
                if (!(rating.getRating() instanceof Double)) {
                    throw new IllegalArgumentException("Rating for ".concat(discipline.toString())
                            .concat(" must be double number"));
                }
                break;
        }
    }

    public Map<Discipline,Rating> getDisciplineMap() {
        return disciplineMap;
    }

    public String getName() {
        return name;
    }

    public String getSubName() {
        return subName;
    }

    public Gender getGender() {
        return gender;
    }
}

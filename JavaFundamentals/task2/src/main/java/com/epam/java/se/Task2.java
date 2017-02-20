package com.epam.java.se;

/**
 * Created by chris on 20.02.2017.
 */
public class Task2 {

    private static final double epsilon = 0.001;

    private static double getValue(int number) {
        return 1 / Math.pow(number + 1, 2);
    }

    public static void main(String[] args) {
        int index = 1;

        while (epsilon < getValue(index)) {
            System.out.println(getValue(index));
            index += 1;
        }

        System.out.println(index);
    }
}
package com.epam.java.se;

/**
 * Created by chris on 20.02.2017.
 */
public class Task3 {

    private static final double epsilon = 0.001;

    private static final double step = 0.01;

    private static final double leftBound = -1.5;

    private static final double rightBound = 1.5;

    private static double getValueOfTheFunction(double argument) {
        return Math.tan(2 * argument) - 3;
    }

    public static void main(String[] args) {
        System.out.println("The value of the argument       |       The value of the function");

        double argument = leftBound;
        
        while (Math.abs(rightBound - argument) > epsilon){
            argument += step;
            System.out.println(argument + "       |       " + getValueOfTheFunction(argument));
        }
    }

}

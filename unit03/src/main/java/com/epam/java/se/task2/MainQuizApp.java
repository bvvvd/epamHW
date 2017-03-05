package com.epam.java.se.task2;

import java.util.Scanner;

public class MainQuizApp {
    public static void main(String[] args) {
        System.out.println("Choose language:");
        Quiz.printAvailableLanguages();
        Quiz quiz;
        boolean quizIsConstructed = false;

        while (!quizIsConstructed) {
            try {
                Scanner scanner = new Scanner(System.in);
                quiz = new Quiz(scanner.nextLine());
                quizIsConstructed = true;
            }catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

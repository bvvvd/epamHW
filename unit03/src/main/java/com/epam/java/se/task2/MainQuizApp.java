package com.epam.java.se.task2;

import java.util.Scanner;

public class MainQuizApp {
    public static void main(String[] args) {
        System.out.println("Choose language:");
        Quiz.printAvailableLanguages();
        Quiz quiz = null;
        boolean quizIsConstructed = false;

        while (!quizIsConstructed) {
            try {
                Scanner scanner = new Scanner(System.in);
                quiz = new Quiz(scanner.nextLine());
                quizIsConstructed = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        String questionOfferAnswer = "";
        quiz.printQuestionOffer();
        final Scanner scanNumberOfQuestion = new Scanner(System.in);

        while (!questionOfferAnswer.equals("stop")) {
            String numberOfQuestion = scanNumberOfQuestion.nextLine();
            boolean numberIsCorrect = false;
            while (!numberIsCorrect) {
                try {
                    if (numberOfQuestion.equals("stop")) {
                        return;
                    }
                    System.out.println(quiz.getQuestion(Integer.parseInt(numberOfQuestion)));
                    numberIsCorrect = true;
                } catch (NumberFormatException e) {
                    quiz.printTypingRequirements();
                    quiz.printQuestionOffer();
                    numberOfQuestion = scanNumberOfQuestion.nextLine();
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    quiz.printQuestionOffer();
                    numberOfQuestion = scanNumberOfQuestion.nextLine();
                }
            }
            String answer = scanNumberOfQuestion.nextLine();
            if (answer.equals(quiz.getAnswer(Integer.parseInt(numberOfQuestion)))) {
                System.out.println("kaef");
            } else {
                System.out.println("ne kaef");
            }
            quiz.printQuestionOffer();
        }
    }

}

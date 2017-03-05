package com.epam.java.se.task2;

import java.util.Scanner;

public class MainQuizApp {
    public static void main(String[] args) {
        final Quiz quiz = initQuiz();

        play(quiz);
    }

    private static Quiz initQuiz() {
        System.out.println("Choose language:");
        Quiz.printAvailableLanguages();
        return buildQuiz();
    }

    private static void play(Quiz quiz) {
        quiz.printQuestionOffer();
        final Scanner scanNumberOfQuestion = new Scanner(System.in);
        String questionOfferAnswer = "";

        while (!questionOfferAnswer.equals("stop")) {
            questionOfferAnswer = makeOfferForQuestion(scanNumberOfQuestion);

            questionOfferAnswer = chooseQuestion(quiz, scanNumberOfQuestion, questionOfferAnswer);

            if (checkAnswer(quiz, scanNumberOfQuestion, questionOfferAnswer)) return;

            quiz.printQuestionOffer();
        }
    }

    private static boolean checkAnswer(Quiz quiz, Scanner scanNumberOfQuestion, String questionOfferAnswer) {
        if (questionOfferAnswer == null) return true;
        String answer = scanNumberOfQuestion.nextLine();
        if (answer.equalsIgnoreCase(quiz.getAnswer(Integer.parseInt(questionOfferAnswer)))) {
            System.out.println("+");
        } else {
            System.out.println("-");
        }
        return false;
    }

    private static String makeOfferForQuestion(Scanner scanNumberOfQuestion) {
        String questionOfferAnswer;
        questionOfferAnswer = scanNumberOfQuestion.nextLine();
        return questionOfferAnswer;
    }

    private static String chooseQuestion(Quiz quiz, Scanner scanNumberOfQuestion, String questionOfferAnswer) {
        boolean numberIsCorrect = false;
        while (!numberIsCorrect) {
            if (questionOfferAnswer.equals("stop")) {
                return null;
            }
            try {
                System.out.println(quiz.getQuestion(Integer.parseInt(questionOfferAnswer)));
                numberIsCorrect = true;
            } catch (NumberFormatException e) {
                quiz.printTypingRequirements();
                questionOfferAnswer = makeDialogWithUser(quiz, scanNumberOfQuestion);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                questionOfferAnswer = makeDialogWithUser(quiz, scanNumberOfQuestion);
            }
        }
        return questionOfferAnswer;
    }

    private static String makeDialogWithUser(Quiz quiz, Scanner scanNumberOfQuestion) {
        String numberOfQuestion;
        quiz.printQuestionOffer();
        numberOfQuestion = makeOfferForQuestion(scanNumberOfQuestion);
        return numberOfQuestion;
    }

    private static Quiz buildQuiz() {
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

        return quiz;
    }

}

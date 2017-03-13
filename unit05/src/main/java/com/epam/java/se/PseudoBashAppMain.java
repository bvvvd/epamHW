package com.epam.java.se;

import com.epam.java.se.exceptions.DirectoryRemovingException;
import com.epam.java.se.exceptions.FileNotExistException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class PseudoBashAppMain {
    final static Scanner scanner = new Scanner(System.in);
    final static Nautilus nautilus = new Nautilus();
    final static FileEditor editor = new FileEditor();
    final static FileContentEditor contentEditor = new FileContentEditor();

    public static void main(String[] args) {
        while (true) {
            printWorkingDirectory();
            try {
                recognizeUserAnswer(readUserAnswer());
            } catch (IOException | DirectoryRemovingException | FileNotExistException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void recognizeUserAnswer(String stringWithAnswer) throws IOException, FileNotExistException, DirectoryRemovingException {
        StringTokenizer tokenizer = new StringTokenizer(stringWithAnswer, " ");
        StringTokenizer tokenizerToMethods = tokenizer;
        String token = tokenizer.nextToken();
        switch (token) {
            case "ls":
                printDirectoryContent();
                break;
            case "pwd":
                printWorkingDirectory();
                break;
            case "cd":
                changeDirectory(tokenizerToMethods);
                break;
            case "touch":
                createNewFile(tokenizerToMethods);
                break;
            case "mkdir":
                createNewDirectory(tokenizerToMethods);
                break;
            case "cat":
                writeFileContent(tokenizerToMethods);
                break;
            case "catt":
                appendFileContent(tokenizerToMethods);
                break;
            case "rm":
                remove(tokenizerToMethods);
                break;
            case "exit":
                System.exit(0);
            default:
                throw new UnsupportedOperationException("Inserted operation is not supported");
        }
    }

    private static void appendFileContent(StringTokenizer tokenizer) throws IOException {
        final String currentDirectory = nautilus.pwd();
        tokenizer.nextToken();

        final String fileName = tokenizer.nextToken();
        final String fullFileName = makeFullFileName(currentDirectory, fileName);
        final StringBuilder content = new StringBuilder();

        while (tokenizer.hasMoreTokens()) {
            content.append(tokenizer.nextToken());
        }

        contentEditor.catt(fullFileName, content.toString());
    }

    private static void writeFileContent(StringTokenizer tokenizer) throws IOException {
        final String currentDirectory = nautilus.pwd();
        tokenizer.nextToken();

        final String fileName = tokenizer.nextToken();
        final String fullFileName = makeFullFileName(currentDirectory, fileName);
        final StringBuilder content = new StringBuilder();

        while (tokenizer.hasMoreTokens()) {
            content.append(tokenizer.nextToken());
        }

        contentEditor.cat(fullFileName, content.toString());
    }

    private static void changeDirectory(StringTokenizer tokenizer) throws FileNotExistException {
        final String currentDirectory = nautilus.pwd();
        tokenizer.nextToken();

        final String fileName = tokenizer.nextToken();

        final String fullFileName = makeFullFileName(currentDirectory, fileName);

        nautilus.cd(fullFileName);

    }

    private static void remove(StringTokenizer tokenizer) throws FileNotExistException, DirectoryRemovingException {
        final String currentDirectory = nautilus.pwd();
        tokenizer.nextToken();

        while (tokenizer.hasMoreTokens()) {
            final String fileName = tokenizer.nextToken();

            final String fullFileName = makeFullFileName(currentDirectory, fileName);

            editor.rm(fullFileName);
        }
    }

    private static void createNewDirectory(StringTokenizer tokenizer) throws FileAlreadyExistsException {
        final String currentDirectory = nautilus.pwd();
        tokenizer.nextToken();

        while (tokenizer.hasMoreTokens()) {
            final String directoryName = tokenizer.nextToken();

            final String fullDirectoryName = makeFullFileName(currentDirectory, directoryName);

            editor.mkdir(fullDirectoryName);
        }
    }

    private static void createNewFile(StringTokenizer tokenizer) throws IOException {
        final String currentDirectory = nautilus.pwd();
        tokenizer.nextToken();

        while (tokenizer.hasMoreTokens()) {
            final String fileName = tokenizer.nextToken();

            final String fullFileName = makeFullFileName(currentDirectory, fileName);

            editor.touch(fullFileName);
        }
    }

    private static String makeFullFileName(String currentDirectory, String fileName) {
        return currentDirectory.concat("\\").concat(fileName);
    }

    private static void printWorkingDirectory() {
        System.out.println(nautilus.pwd());
    }

    private static void printDirectoryContent() {
        Arrays.stream(nautilus.ls()).forEach((file) -> System.out.println(file.getName()));
    }

    private static String readUserAnswer() {
        return scanner.nextLine();
    }

}

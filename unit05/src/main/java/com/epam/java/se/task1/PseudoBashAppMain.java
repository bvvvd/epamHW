package com.epam.java.se.task1;

import com.epam.java.se.task1.exceptions.DirectoryRemovingException;
import com.epam.java.se.task1.exceptions.FileNotExistException;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.*;

public class PseudoBashAppMain {
    final static Scanner scanner = new Scanner(System.in);
    final static Nautilus nautilus = new Nautilus();
    final static FileEditor editor = new FileEditor();
    final static FileContentEditor contentEditor = new FileContentEditor();
    private static String pathSeparator = System.getProperty("file.separator");

    public static void main(String[] args) {
        while (true) {
            printWorkingDirectory();
            try {
                recognizeUserAnswer(readUserAnswer());
            } catch (IOException | DirectoryRemovingException | FileNotExistException |
                    UnsupportedOperationException | IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (NoSuchElementException e) {
                System.out.println("enter a command");
            }
        }
    }

    private static void recognizeUserAnswer(String stringWithAnswer)
            throws IOException, FileNotExistException, DirectoryRemovingException, NoSuchElementException {
        StringTokenizer tokenizer = new StringTokenizer(stringWithAnswer, " ");
        String token = tokenizer.nextToken();

        switch (token) {
            case "ls"://+
                printDirectoryContent();
                break;
            case "pwd"://+
                printWorkingDirectory();
                break;
            case "cd"://+
                changeDirectory(tokenizer);
                break;
            case "touch"://+
                createNewFile(tokenizer);
                break;
            case "mkdir"://+
                createNewDirectory(tokenizer);
                break;
            case "cat"://+
                writeFileContent(tokenizer);
                break;
            case "catt"://+
                appendFileContent(tokenizer);
                break;
            case "rm"://+
                remove(tokenizer);
                break;
            case "exit"://+
                System.exit(0);
            default:
                throw new UnsupportedOperationException("Inserted operation is not supported");
        }
    }

    private static void appendFileContent(StringTokenizer tokenizer) throws IOException {
        final String currentDirectory = nautilus.pwd();

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

        final String fileName = tokenizer.nextToken();
        final String fullFileName = makeFullFileName(currentDirectory, fileName);
        final StringBuilder content = new StringBuilder();

        if (!tokenizer.hasMoreTokens()) {
            System.out.println(contentEditor.cat(fullFileName));
            return;
        }

        while (tokenizer.hasMoreTokens()) {
            content.append(tokenizer.nextToken());
        }

        contentEditor.cat(fullFileName, content.toString());
    }

    private static void changeDirectory(StringTokenizer tokenizer) throws FileNotExistException {
        final String currentDirectory = nautilus.pwd();

        if (!tokenizer.hasMoreTokens()) {
            nautilus.cd();
            return;
        }

        final String fileName = tokenizer.nextToken();

        if (fileName.equals("..")) {
            nautilus.cd("..");
            return;
        }


        if (fileName.contains(":\\")) {
            nautilus.cd(fileName);
            return;
        }

        final String fullFileName = makeFullFileName(currentDirectory, fileName);

        nautilus.cd(fullFileName);

    }

    private static void remove(StringTokenizer tokenizer) throws FileNotExistException, DirectoryRemovingException {
        final String currentDirectory = nautilus.pwd();

        while (tokenizer.hasMoreTokens()) {
            final String fileName = tokenizer.nextToken();

            final String fullFileName = makeFullFileName(currentDirectory, fileName);

            editor.rm(fullFileName);
        }
    }

    private static void createNewDirectory(StringTokenizer tokenizer) throws FileAlreadyExistsException {
        final String currentDirectory = nautilus.pwd();

        while (tokenizer.hasMoreTokens()) {
            final String directoryName = tokenizer.nextToken();

            final String fullDirectoryName = makeFullFileName(currentDirectory, directoryName);

            editor.mkdir(fullDirectoryName);
        }
    }

    private static void createNewFile(StringTokenizer tokenizer) throws IOException {
        final String currentDirectory = nautilus.pwd();

        while (tokenizer.hasMoreTokens()) {
            final String fileName = tokenizer.nextToken();

            final String fullFileName = makeFullFileName(currentDirectory, fileName);

            editor.touch(fullFileName);
        }
    }

    private static String makeFullFileName(String currentDirectory, String fileName) {
        return currentDirectory.concat(pathSeparator).concat(fileName);
    }

    private static void printWorkingDirectory() {
        System.out.print(nautilus.pwd());
    }

    private static void printDirectoryContent() {
        Arrays.stream(nautilus.ls()).forEach((file) -> System.out.println(file.getName()));
    }

    private static String readUserAnswer() {
        return scanner.nextLine();
    }

}
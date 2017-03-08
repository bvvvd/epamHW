package com.epam.java.se.task3;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PictureReferenceAnalyzer {
    private final File file;
    private Pattern referencePattern = Pattern.compile("[Р|р]ис(\\.)?([унок|унки|унках|унком|])*");
    ;

    public PictureReferenceAnalyzer(String fileName) {
        this.file = new File(fileName);
    }

    public String extractSourceText() throws FileNotFoundException, UnsupportedEncodingException {
        final BufferedReader htmlTextReader = new BufferedReader(
                new InputStreamReader(new FileInputStream(fileName()), "windows-1251"));
        final StringBuilder htmlText = new StringBuilder();

        htmlTextReader.lines().forEach(htmlText::append);

        return htmlText.toString();
    }

    private String fileName() {
        return file.getName();
    }

    public File getFile() {
        return new File(String.valueOf(file));
    }

    public void makeOutputHtml() throws FileNotFoundException, UnsupportedEncodingException {
        final BufferedReader fileReader = new BufferedReader(
                new InputStreamReader(new FileInputStream(file), "windows-1251"));


        final BufferedWriter fileWriter = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream("output.html"), "windows-1251"));

        try {
            String currentLine;
            do {
                currentLine = fileReader.readLine();
                fileWriter.write(currentLine);
            } while (!currentLine.equals("<br style=\"clear:both;\">"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> textLines = extractTextWithoutHtml();
        textLines.forEach((line) -> {
            try {
                fileWriter.write(line);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public List<String> extractTextWithoutHtml() throws FileNotFoundException, UnsupportedEncodingException {
        List<String> textLines = extractTextWithoutStyle();

        return makeStrong(textLines);
    }

    private List<String> makeStrong(List<String> textLines) {
        List<String> result = new ArrayList<>();
        Matcher matcher;
        for (String line : textLines) {
            matcher = referencePattern.matcher(line);
            if (matcher.find()) {
                line = strong(line);
            }
            result.add(line);
        }

        return result;
    }

    private String strong(String line) {
        return "<b>" + line + "</b>";
    }

    private List<String> extractTextWithoutStyle() throws UnsupportedEncodingException, FileNotFoundException {
        final BufferedReader fileReader = new BufferedReader(
                new InputStreamReader(new FileInputStream(file), "windows-1251"));

        skipUnnecessaryLines(fileReader);

        return readNecessaryLines(fileReader);
    }

    private List<String> readNecessaryLines(BufferedReader fileReader) {
        List<String> result = new ArrayList<>();
        String currentLine;
        try {
            while ((currentLine = fileReader.readLine()) != null) {
                if (!currentLine.isEmpty()) {
                    result.add(currentLine);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void skipUnnecessaryLines(BufferedReader fileReader) {
        try {
            String currentLine;
            do {
                currentLine = fileReader.readLine();
            } while (!currentLine.equals("<br style=\"clear:both;\">"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

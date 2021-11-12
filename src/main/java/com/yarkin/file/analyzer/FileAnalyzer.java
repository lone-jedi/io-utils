package com.yarkin.file.analyzer;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class FileAnalyzer {

    private File file;

    public FileAnalyzer() {}

    public FileAnalyzer(File file) {
        this.file = file;
    }

    public FileAnalyzer(String pathToFile) {
        file = new File(pathToFile);
    }

    public FileData analyze(String substring) throws IOException {
        String fileContent = fileGetContent();
        char[] delimiters = {'.', '!', '?'};
        ArrayList<String> sentences = explode(fileContent, delimiters);
        ArrayList<String> matchSentences = getMatches(sentences, substring);
        int matchesCount = numberOfMatches(fileContent, substring);

        return new FileData(substring, matchSentences, matchesCount);
    }

    public static int numberOfMatches(String text, String substring) {
        int totalCount = 0;
        int textLength = text.length();
        int substringLength = substring.length();

        for (int i = 0; i < textLength - substringLength; i++) {
            if(text.charAt(i) == substring.charAt(0)) {
                if(text.substring(i, i + substringLength).equals(substring)) {
                    totalCount++;
                }
            }
        }

        return totalCount;
    }

    public static ArrayList<String> getMatches(ArrayList<String> sentences, String substring) {
        ArrayList<String> result = new ArrayList<>();
        for (String sentence : sentences) {
            if(sentence.contains(substring)) {
                result.add(sentence);
            }
        }
        return result;
    }

    public static ArrayList<String> explode(String text, char[] delimiters) {
        ArrayList<String> result = new ArrayList<>();

        int lastIndexOfDelimiter = -1;
        int textLength = text.length();
        for (int i = 0; i < textLength; i++) {
                if(isDelimiter(text.charAt(i), delimiters)) {
                    if(i + 1 < textLength && isDelimiter(text.charAt(i + 1), delimiters)) {
                        continue;
                    }

                    String substring = text.substring(lastIndexOfDelimiter + 1, i + 1);
                    lastIndexOfDelimiter = i;
                    result.add(substring.trim());
                }
        }

        if(lastIndexOfDelimiter == -1) {
            result.add(text);
            return result;
        }

        if (lastIndexOfDelimiter != textLength - 1) {
            result.add(text.substring(lastIndexOfDelimiter + 1, textLength).trim());
        }

        return result;
    }

    private static boolean isDelimiter(char value, char[] delimiters) {
        for (char delimiter : delimiters) {
            if(value == delimiter) {
                return true;
            }
        }
        return false;
    }

    public String fileGetContent() throws IOException {
        InputStream input = new FileInputStream(file);
        byte[] fileBytes = input.readAllBytes();
        input.close();
        return new String(fileBytes);
    }

}

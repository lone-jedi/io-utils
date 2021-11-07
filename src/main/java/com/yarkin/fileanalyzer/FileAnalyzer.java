package com.yarkin.fileanalyzer;

import java.io.InputStream;
import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.util.ArrayList;

public class FileAnalyzer {
    private int totalMatches;
    private File file;

    public static void main(String... args) throws IOException {
        if(args.length == 0) {
            System.out.println("Error! Missed path to file argument. Write --help to see right usage");
            return;
        }

        if(args[0].equals("--help")) {
            System.out.println("Usage:   java FileAnalyzer [path-to-file] [searched-string]");
            System.out.println("Example: java FileAnalyzer C:\\test.txt \"Searched string\"");
            return;
        }

        if(args.length == 1) {
            System.out.println("Error! Missed argument of the searched string. Write --help to see right usage");
            return;
        }

        String path = args[0];
        String searched = args[1];
        File file = new File(path);

        if(!file.exists()) {
            System.out.println("Error! File does not exist. Write --help to see right usage");
            return;
        }

        if(!file.isFile()) {
            System.out.println("Error! Received path not referred to file. Write --help to see right usage");
            return;
        }

        if(!file.canRead()) {
            System.out.println("Error! Forbidden to read this file. Write --help to see right usage");
            return;
        }
        FileAnalyzer fileAnalyzer = new FileAnalyzer(file);
        fileAnalyzer.printMatches(searched);
    }

    public FileAnalyzer(File file) {
        this.file = file;
    }

    public int getTotalMatches() {
        return totalMatches;
    }

    public File getFileName() {
        return file;
    }

    public void printMatches(String searchedValue) throws IOException {
        ArrayList<String> sentences = getMatches(searchedValue);
        for (String sentence : sentences) {
            System.out.println(sentence);
        }

        System.out.println("TOTAL: " + totalMatches);

    }

    public ArrayList<String> getMatches(String searchedValue) throws IOException {
        ArrayList<String> result = new ArrayList<>();
        totalMatches = 0;
        InputStream input = new FileInputStream(file);
        int lastDelimiter = -1;
        boolean isSearched = false;
        for(int current = input.read(), i = 0; current != -1; current = input.read(), ++i) {
            char currentLetter = (char) current;

            if(isDelimiter(currentLetter)) {
                if(isSearched) {
                    String currentSentence = getSubstring(new FileInputStream(file), lastDelimiter + 1, i - lastDelimiter + 1);
                    isSearched = false;
                    totalMatches++;
                    result.add(clear(currentSentence));
                }
                lastDelimiter = i;

            }

            if(current == searchedValue.charAt(0)) {
                String possibleValue = getSubstring(new FileInputStream(file), i, searchedValue.getBytes().length);

                if(possibleValue == null) {
                    continue;
                }
                if(possibleValue.equals(searchedValue)) {
                    isSearched = true;
                }
            }
        }
        input.close();
        return result;
    }

    private String clear(String value) {
        String trimmedValue = value.trim();

        StringBuilder resultBuilder = new StringBuilder();
        int trimmedValueLength = trimmedValue.length();
        for (int i = 0; i < trimmedValueLength; ++i) {
            char current = trimmedValue.charAt(i);
            if(isJunk(current)) {
                continue;
            }
            resultBuilder.append(current);
        }

        return resultBuilder.toString();
    }

    private boolean isJunk(char value) {
        return value == '\n' && value == '\r';
    }

    private String getSubstring(InputStream input, long offset, int length) throws IOException {
        String result = null;
        byte[] possibleValueBytes = new byte[length];

        try {
            if(input.skip(offset) != offset) {
                throw new IndexOutOfBoundsException();
            }
            input.read(possibleValueBytes, 0, length);
            result = new String(possibleValueBytes);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        } finally {
            input.close();
        }

        return result;
    }

    private boolean isDelimiter(char letter) {
        return letter == '.' || letter == '!' || letter == '?';
    }

}

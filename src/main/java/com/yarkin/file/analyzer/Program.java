package com.yarkin.file.analyzer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Program {
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
        String searchedValue = args[1];
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
        // ArrayList<String> matchSentences = fileAnalyzer.getMatches(searchedValue);
        // int countMatches = fileAnalyzer.getCountOfMatches(searchedValue);

        System.out.println("---- Sentences with \"" + searchedValue + "\" ----");
        // printSentences(matchSentences);
        // System.out.println("TOTAL NUMBER OF MATCHES: " + countMatches);
    }

    public static void printSentences(ArrayList<String> sentences) {
        for (String sentence : sentences) {
            System.out.println(sentence);
        }
    }
}

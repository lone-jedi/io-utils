package com.yarkin.file.analyzer;

import java.util.ArrayList;

public class FileData {
    private String substring;
    private ArrayList<String> matchSentences;
    private int numberOfMatches;

    public FileData(String substring, ArrayList<String> matchSentences, int numberOfMatches) {
        this.substring = substring;
        this.matchSentences = matchSentences;
        this.numberOfMatches = numberOfMatches;
    }


    public String getSubstring() {
        return substring;
    }

    public ArrayList<String> getMatchSentences() {
        return matchSentences;
    }

    public int getNumberOfMatches() {
        return numberOfMatches;
    }
}

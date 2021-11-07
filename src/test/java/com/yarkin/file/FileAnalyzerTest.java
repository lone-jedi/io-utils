package com.yarkin.file;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FileAnalyzerTest {
    private FileAnalyzer fileAnalyzer;

    @BeforeEach
    public void before() {
        fileAnalyzer = new FileAnalyzer(new File(TestFile.ASCII.toString()));
    }

    @Test
    public void testGetMatchesFromStartSentenceAndTotalMatches() throws IOException {
        ArrayList<String> expected = new ArrayList<>();
        expected.add("Start.");
        expected.add("Some sentence?");
        expected.add("JUnit is the powerful testing framework!");

        ArrayList<String> actual = fileAnalyzer.getMatches("t");

        assertEquals(expected, actual);
        assertEquals(7, fileAnalyzer.getLastTotalMatches());
    }

    @Test
    public void testGetMatchesFromFirstSentence() throws IOException {
        ArrayList<String> expected = new ArrayList<>();
        expected.add("JUnit is the powerful testing framework!");

        ArrayList<String> actual = fileAnalyzer.getMatches("JUnit");

        assertEquals(expected, actual);
    }

    @Test
    public void testGetMatchesFromLastSentence() throws IOException {
        ArrayList<String> expected = new ArrayList<>();
        expected.add("JUnit is the powerful testing framework!");

        ArrayList<String> actual = fileAnalyzer.getMatches("framework");

        assertEquals(expected, actual);
    }

    @Test
    public void testGetMatchesFromStart() throws IOException {
        ArrayList<String> expected = new ArrayList<>();
        expected.add("Start.");
        ArrayList<String> actual = fileAnalyzer.getMatches( "Start");

        assertEquals(expected, actual);
    }

    @Test
    public void testGetMatchesFromEnd() throws IOException {
        ArrayList<String> expected = new ArrayList<>();
        expected.add("End.");
        ArrayList<String> actual = fileAnalyzer.getMatches("End");

        assertEquals(expected, actual);
    }

    @Test
    public void testGetMatchesFromEmptyFile() throws IOException {
        FileAnalyzer fileAnalyzer = new FileAnalyzer(new File(TestFile.EMPTY.toString()));
        ArrayList<String> actual = fileAnalyzer.getMatches("test");
        assertTrue(actual.isEmpty());
    }
}

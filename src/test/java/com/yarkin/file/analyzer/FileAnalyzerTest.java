package com.yarkin.file.analyzer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileAnalyzerTest {
    private final char[] delimiters = {'.', '!', '?'};

    @BeforeEach
    public void setupFileAnalyzer() {

    }

    @Test
    public void getContentFromFile() throws IOException {
        FileAnalyzer fileAnalyzer = new FileAnalyzer(TestFile.ASCII.toString());
        String expected = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Nunc lacus nisl, posuere eu tincidunt ac, lobortis sed orci. " +
                "Curabitur commodo sagittis ornare. Nullam at leo blandit, maximus arcu a, lacinia nisi. " +
                "Donec posuere ante euismod iaculis consectetur. Proin cursus nisi fringilla convallis imperdiet. " +
                "Phasellus a accumsan elit, vitae congue felis. Integer nec justo nunc. " +
                "Suspendisse sem libero, iaculis eu metus vel, blandit sagittis massa. " +
                "Vestibulum tincidunt luctus lectus, eget sodales dolor. " +
                "Praesent felis leo, tincidunt vitae tellus ac, hendrerit interdum quam. " +
                "Nulla a massa dignissim, condimentum enim malesuada, convallis nibh. " +
                "Interdum et malesuada fames ac ante ipsum primis in faucibus. Maecenas gravida ut nunc ut pharetra.";
        String actual = fileAnalyzer.fileGetContent();
        assertEquals(expected, actual);
    }

    @Test
    public void getContentFromEmptyFile() throws IOException {
        FileAnalyzer fileAnalyzer = new FileAnalyzer(TestFile.EMPTY.toString());
        String actual = fileAnalyzer.fileGetContent();
        assertEquals("", actual);
    }

    @Test
    public void getContentFromFileWithRussianSymbols() throws IOException {
        FileAnalyzer fileAnalyzer = new FileAnalyzer(TestFile.RU_TEXT.toString());
        String expected = "Ночь, улица, фонарь, аптека," + System.lineSeparator() +
                        "Бессмысленный и тусклый свет." + System.lineSeparator() +
                        "Живи еще хоть четверть века —" + System.lineSeparator() +
                        "Всё будет так. Исхода нет.";
        String actual = fileAnalyzer.fileGetContent();
        assertEquals(expected, actual);
    }

    @Test
    public void explodeStringByDelimiters() {
        ArrayList<String> expected = new ArrayList<>();
        expected.add("Hello!");
        expected.add("I am Alex.");
        expected.add("Can I join you?");
        ArrayList<String> actual = FileAnalyzer.explode("Hello! I am Alex. Can I join you?", delimiters);

        assertEquals(expected, actual);
    }

    @Test
    public void explodeStringWhenDelimitersNotFound() {
        ArrayList<String> expected = new ArrayList<>();
        expected.add("Hello");
        ArrayList<String> actual = FileAnalyzer.explode("Hello", delimiters);

        assertEquals(expected, actual);
    }

    @Test
    public void explodeStringWhenDelimiterNotFoundAtTheEnd() {
        ArrayList<String> expected = new ArrayList<>();
        expected.add("Hello?");
        expected.add("Anybody here");
        ArrayList<String> actual = FileAnalyzer.explode("Hello? Anybody here", delimiters);

        assertEquals(expected, actual);
    }

    @Test
    public void explodeStringWhenDelimitersDuplicated() {
        ArrayList<String> expected = new ArrayList<>();
        expected.add("Hello???");
        expected.add("Anybody here?!");
        ArrayList<String> actual = FileAnalyzer.explode("Hello??? Anybody here?!", delimiters);

        assertEquals(expected, actual);
    }

    @Test
    public void explodeStringWhichContainsOnlyDelimiters() {
        ArrayList<String> expected = new ArrayList<>();
        expected.add("?.??!!.!?!....?!.");
        ArrayList<String> actual = FileAnalyzer.explode("?.??!!.!?!....?!.", delimiters);
        assertEquals(expected, actual);
    }

    @Test
    public void getMatchesOfWordAtSomeSentences() {
        ArrayList<String> expected = new ArrayList<>();
        expected.add("Java is an object-oriented programming language");
        expected.add("For developing enterprise applications should to use Java");
        expected.add("123456!!310-Java293,.!!");

        ArrayList<String> sentences = new ArrayList<>();
        sentences.add("Java is an object-oriented programming language");
        sentences.add("Some text...");
        sentences.add("For developing enterprise applications should to use Java");
        sentences.add("java core is important to know");
        sentences.add("123456!!310-Java293,.!!");

        ArrayList<String> actual = FileAnalyzer.getMatches(sentences, "Java");
        assertEquals(expected, actual);
    }

    @Test
    public void getMatchesWithNoMatchedSentences() {
        ArrayList<String> sentences = new ArrayList<>();
        sentences.add("Some text...");
        sentences.add("java core is important to know");

        ArrayList<String> actual = FileAnalyzer.getMatches(sentences, "Java");
        assertTrue(actual.isEmpty());
    }

    @Test
    public void countNumberOfLetterMatches() {
        String text = "The car is red";
        int actual = FileAnalyzer.numberOfMatches(text, "e");
        assertEquals(2, actual);
    }

    @Test
    public void countNumberOfLetterMatchesWhenMatchesNotFound() {
        String text = "The car is red";
        int actual = FileAnalyzer.numberOfMatches(text, "z");
        assertEquals(0, actual);
    }

    @Test
    public void countNumberOfWordMatches() {
        String text = "The car is red! Car caR";
        int actual = FileAnalyzer.numberOfMatches(text, "car");
        assertEquals(1, actual);
    }

    @Test
    public void countNumberOfWordMatchesWhenMatchesNotFound() {
        String text = "The car is red";
        int actual = FileAnalyzer.numberOfMatches(text, "test");
        assertEquals(0, actual);
    }
}

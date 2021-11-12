package com.yarkin.file.analyzer;

import com.yarkin.file.TestFile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileAnalyzerUITests {

    private FileAnalyzer fileAnalyzer;

    @BeforeEach
    public void setupFileAnalyzerWithRussianText() {
        fileAnalyzer = new FileAnalyzer(TestFile.RU_TEXT.toString());
    }

    @Test
    public void analyzeRussianFile() throws IOException {
        FileData fileData = fileAnalyzer.analyze("ет");
        assertEquals("ет", fileData.getSubstring());
        assertEquals(4, fileData.getNumberOfMatches());

        ArrayList<String> expected = new ArrayList<>();
        expected.add("Ночь, улица, фонарь, аптека," + System.lineSeparator() + "Бессмысленный и тусклый свет.");
        expected.add("Живи еще хоть четверть века —" + System.lineSeparator() + "Всё будет так.");
        expected.add("Исхода нет.");

        assertEquals(expected, fileData.getMatchSentences());
    }

    @Test
    public void analyzeRussianFileWithoutMatches() throws IOException {
        FileData fileData = fileAnalyzer.analyze("авто");
        assertEquals("авто", fileData.getSubstring());
        assertEquals(0, fileData.getNumberOfMatches());
        assertTrue(fileData.getMatchSentences().isEmpty());
    }
}

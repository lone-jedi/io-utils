package com.yarkin.file;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import java.nio.file.NotDirectoryException;

public class FileManagerTest {

    @Test
    public void testCountFilesWithOnlyDirectories() {
        int expected = 7;
        int actual = FileManager.countFiles(TestDirectory.ONLY_DIRECTORIES.toString());

        assertEquals(expected, actual);
    }

    @Test
    public void testCountFilesWithOnlyFiles() {
        int expected = 10;
        int actual = FileManager.countFiles(TestDirectory.ONLY_FILES.toString());

        assertEquals(expected, actual);
    }

    @Test
    public void testCountFilesInEmptyDirectory() {
        int expected = 0;
        int actual = FileManager.countFiles(TestDirectory.EMPTY.toString());

        assertEquals(expected, actual);
    }

    @Test
    public void testCountFilesWhenThisIsNotDirectory() {
        assertThrows(NotDirectoryException.class, () -> {
            FileManager.countFiles(TestFile.ASCII.toString());
        });
    }
}

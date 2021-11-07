package com.yarkin.file;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import java.nio.file.NotDirectoryException;

public class FileManagerTest {

    @Test
    public void testCountFilesWithOnlyDirectories() throws NotDirectoryException {
        int expected = 0;
        int actual = FileManager.countFiles(TestDirectory.ONLY_DIRECTORIES.toString());

        assertEquals(expected, actual);
    }

    @Test
    public void testCountFilesWithOnlyFiles() throws NotDirectoryException {
        int expected = 2;
        int actual = FileManager.countFiles(TestDirectory.ONLY_FILES.toString());

        assertEquals(expected, actual);
    }

    @Test
    public void testCountFilesInEmptyDirectory() throws NotDirectoryException {
        int expected = 0;
        int actual = FileManager.countFiles(TestDirectory.EMPTY.toString());

        assertEquals(expected, actual);
    }

    @Test
    public void testCountFiles() throws NotDirectoryException {
        int expected = 3;
        int actual = FileManager.countFiles(TestDirectory.WITH_FILES.toString());

        assertEquals(expected, actual);
    }

    @Test
    public void testCountFilesWhenThisIsNotDirectory() {
        assertThrows(NotDirectoryException.class, () -> {
            FileManager.countFiles(TestFile.ASCII.toString());
        });
    }
}

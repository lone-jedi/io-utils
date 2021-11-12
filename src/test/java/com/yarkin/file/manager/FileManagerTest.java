package com.yarkin.file.manager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.yarkin.file.TestDirectory;
import com.yarkin.file.TestFile;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.NotDirectoryException;

public class FileManagerTest {

    @BeforeAll
    public static void initialize() {
        // Create empty directory
        new File(TestDirectory.EMPTY.toString()).mkdirs();

        // Create directory with empty directories
        File directoryA = new File(TestDirectory.ONLY_DIRECTORIES.toString(), "a");
        directoryA.mkdirs();
        new File(directoryA, "a1").mkdirs();
        new File(directoryA, "a2").mkdirs();
        File directoryA3 = new File(directoryA, "a3");
        directoryA3.mkdirs();
        new File(directoryA3, "a31").mkdirs();
        new File(TestDirectory.ONLY_DIRECTORIES.toString(), "b").mkdirs();
        new File(TestDirectory.ONLY_DIRECTORIES.toString(), "c").mkdirs();
    }

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

    ////////////////

    @Test
    public void testCountDirsWithOnlyDirectories() throws NotDirectoryException {
        int expected = 7;
        int actual = FileManager.countDirs(TestDirectory.ONLY_DIRECTORIES.toString());

        assertEquals(expected, actual);
    }

    @Test
    public void testCountDirsWithOnlyFiles() throws NotDirectoryException {
        int expected = 0;
        int actual = FileManager.countDirs(TestDirectory.ONLY_FILES.toString());

        assertEquals(expected, actual);
    }

    @Test
    public void testCountDirsInEmptyDirectory() throws NotDirectoryException {
        int expected = 0;
        int actual = FileManager.countDirs(TestDirectory.EMPTY.toString());

        assertEquals(expected, actual);
    }

    @Test
    public void testCountDirs() throws NotDirectoryException {
        int expected = 7;
        int actual = FileManager.countDirs(TestDirectory.WITH_FILES.toString());

        assertEquals(expected, actual);
    }

    @Test
    public void testCountDirsWhenThisIsNotDirectory() {
        assertThrows(NotDirectoryException.class, () -> {
            FileManager.countDirs(TestFile.ASCII.toString());
        });
    }

    /////////////////

    @Test
    public void testCopy() throws IOException {
        FileManager.copy(TestDirectory.COPY_FROM.toString(), TestDirectory.COPY_TO.toString());

    }

    @Test
    public void testDelete() throws IOException {
        FileManager.delete(TestDirectory.COPY_TO.toString());
    }
}

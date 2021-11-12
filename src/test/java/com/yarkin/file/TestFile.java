package com.yarkin.file.analyzer;

public enum TestFile {
    ASCII("src/test/resources/filemanager/testfiles/test.txt"),
    RU_TEXT("src/test/resources/filemanager/testfiles/ru.txt"),
    EMPTY("src/test/resources/filemanager/testfiles/empty.txt");

    private String path;

    TestFile(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return path;
    }
}

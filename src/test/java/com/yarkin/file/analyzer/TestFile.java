package com.yarkin.file;

public enum TestFile {
    ASCII("src/test/resources/filemanager/testfiles/test.txt"),
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

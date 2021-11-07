package com.yarkin.file;

public enum TestFile {
    ASCII("src/test/java/com/yarkin/file/testfiles/test.txt"),
    EMPTY("src/test/java/com/yarkin/file/testfiles/empty.txt");

    private String path;

    TestFile(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return path;
    }
}

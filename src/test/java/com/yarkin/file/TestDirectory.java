package com.yarkin.file;

public enum TestDirectory {
    ONLY_DIRECTORIES("src/test/java/com/yarkin/file/testdirs"),
    WITH_FILES("src/test/java/com/yarkin/file/dirswithfiles"),
    ONLY_FILES("src/test/java/com/yarkin/file/testfiles"),
    COPY_FROM("src/test/java/com/yarkin/file/testcopy/from"),
    COPY_TO("src/test/java/com/yarkin/file/testcopy/to"),
    EMPTY("src/test/java/com/yarkin/file/emptydir");

    private String path;

    TestDirectory(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return path;
    }
}

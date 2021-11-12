package com.yarkin.file;

public enum TestDirectory {
    ONLY_DIRECTORIES("src/test/resources/filemanager/testdirs"),
    WITH_FILES("src/test/resources/filemanager/dirswithfiles"),
    ONLY_FILES("src/test/resources/filemanager/testfiles"),
    COPY_FROM("src/test/resources/filemanager/testcopy/from"),
    COPY_TO("src/test/resources/filemanager/testcopy/to"),
    EMPTY("src/test/resources/filemanager/emptydir");

    private String path;

    TestDirectory(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return path;
    }
}

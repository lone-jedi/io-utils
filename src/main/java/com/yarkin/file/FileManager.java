package com.yarkin.file;

import java.io.File;
import java.nio.file.NotDirectoryException;

public class FileManager {
    public static int countFiles(String path) throws NotDirectoryException {
        File file = new File(path);

        if(!file.isDirectory()) {
            throw new NotDirectoryException("\"" + path + "\"" + " - is not a directory");
        }

        int totalFiles = 0;
        for (File f : file.listFiles()) {
            if(f.isDirectory()) {
                totalFiles += countFiles(f.getPath());
            } else {
                totalFiles++;
            }

        }

        return totalFiles;
    }
}

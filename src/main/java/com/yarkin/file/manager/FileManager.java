package com.yarkin.file.manager;

import java.io.*;
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

    public static int countDirs(String path) throws NotDirectoryException {
        File file = new File(path);

        if(!file.isDirectory()) {
            throw new NotDirectoryException("\"" + path + "\"" + " - is not a directory");
        }

        int totalDirectories = 0;
        for (File f : file.listFiles()) {
            if(f.isDirectory()) {
                totalDirectories += countDirs(f.getAbsolutePath()) + 1;
            }
        }

        return totalDirectories;
    }

    public static void copy(String from, String to) throws IOException {
        File fileFrom = new File(from);
        File fileTo = new File(to);
        fileTo.mkdir();

        for (File f : fileFrom.listFiles()) {
            if(f.isDirectory()) {
                copy(f.getAbsolutePath(), fileTo.getAbsolutePath() + "\\" + f.getName());
            } else {
                File newFile = new File(fileTo.getAbsolutePath()  + "\\" + f.getName());
                newFile.createNewFile();
                copyFileContent(f, newFile);
            }
        }
    }

    public static void copyFileContent(File from, File to) throws IOException {
        OutputStream output = new FileOutputStream(to);
        InputStream input  = new FileInputStream(from);

        int current;
        while((current = input.read()) != -1) {
            output.write(current);
        }

        output.close();
        input.close();
    }

    public static void move(String from, String to) throws IOException {
        copy(from, to);
        delete(from);
    }

    public static void delete(String path) {
        File file = new File(path);
        for (File f : file.listFiles()) {
            if(f.isDirectory()) {
                delete(f.getAbsolutePath());
            }
            f.delete();
        }
        file.delete();
    }
}

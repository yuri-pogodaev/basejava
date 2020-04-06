package com.basejava.webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

public class MainFile {
    public static void main(String[] args) {
        String filePath = ".\\.gitignore";

        File file = new File(filePath);
        try {
            System.out.println(file.getCanonicalFile());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }
        File dir = new File("./src/com/basejava/webapp");
        System.out.println(dir.isDirectory());
        String[] list = dir.list();
        if (list != null) {
            for (String name : list) {
                System.out.println(name);
            }
        }

        try (FileInputStream fis = new FileInputStream(filePath)) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        final String path = "./src/";
        structure(path);
    }

    private static void structure(String path) {
        File dir = new File(path);
        File[] list;
        try {
            list = dir.listFiles();
            for (File file : Objects.requireNonNull(list)) {
                if (file.isFile()) {
                    System.out.println("File - " + file.getName());
                } else if (file.isDirectory()) {
                    System.out.println("Directory - " + file.getName());
                    structure(file.getCanonicalPath());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
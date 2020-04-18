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
        File content = new File("./src/com/basejava/webapp");
        System.out.println(content.isDirectory());
        String[] list = content.list();
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

        File folder = new File("./src/");
        try {
            printDirDeeply(folder, "");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //https://stackoverflow.com/questions/10655085/print-directory-tree
    //pepino answer
    private static void printDirDeeply(File dir, String tabulator) throws IOException {
        File[] content;
        content = dir.listFiles();
        for (File file : Objects.requireNonNull(content)) {
            if (file.isDirectory()) {
                System.out.println(tabulator + "|-" + file.getName());
                printDirDeeply(file, tabulator + "| ");
            } else {
                System.out.println(tabulator + "+-" + file.getName());
            }
        }
    }
}
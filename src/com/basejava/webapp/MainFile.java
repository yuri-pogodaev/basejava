package com.basejava.webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

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
        if (!folder.exists()) {
            System.out.println(args[0] + "NO exist");
            return;
        }
        structure(folder, "");

    }
    //https://stackoverflow.com/questions/10655085/print-directory-tree
    //pepino answer
    //TODO проверка на 0 listFiles
    private static void structure(File folder, String tabulator) {
        File content[] = folder.listFiles();
        if (content != null) {
            for (File file : content) {
                if (file.isDirectory()) {
                    System.out.println(tabulator + "|-" + file.getName());
                    structure(file, tabulator + "| ");
                } else {
                    System.out.println(tabulator + "+-" + file.getName());
                }
            }
        }
    }
}

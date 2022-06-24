package com.biblioteka;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class FileHelper {
    public static void addObjectToFile(String pathToFile, String objectValue) {
        try {
            FileWriter fw = new FileWriter(pathToFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(objectValue);
            bw.newLine();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

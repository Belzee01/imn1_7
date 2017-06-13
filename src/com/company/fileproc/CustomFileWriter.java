package com.company.fileproc;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CustomFileWriter {

    public static void writeToFile(String data, String filename) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            bw.write(data);

            System.out.println("Done");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
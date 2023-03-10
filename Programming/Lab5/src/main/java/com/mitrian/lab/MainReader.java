package com.mitrian.lab;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MainReader {
    public static void main(String[] args) {
        InputStream is;
        try {
            is = new FileInputStream("/Users/m.n./Desktop/Lab5/src/main/resources/Run.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            while (reader.ready()) {
                String line = reader.readLine();
                lines.add(line);
                System.out.println(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(lines);
    }
}

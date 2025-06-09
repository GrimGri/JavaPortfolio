package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

class file_found {

    String filePath = "D:/test/test.txt";//чтение файла
    String firstLine = null;
    String secondLine = null;
    String thirdLine = null;
    Path path = Paths.get(filePath);
    List<String> lines;

    {
        try {
            lines = Files.readAllLines(path);
            firstLine = lines.get(0);
            secondLine = lines.get(1);
            thirdLine = lines.get(2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
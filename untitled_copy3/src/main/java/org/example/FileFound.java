package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

class FileFound {

    String filePath = GlobalVars.adr;//"D:/test/test.txt";//чтение файла
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
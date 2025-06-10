package org.example;

import java.io.File;//импорт класса для чтения файла
import java.io.IOException;//импорт класса эксепшн

public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File(GlobalVars.adr);
        PerspectiveMan man1 = new PerspectiveMan();
        if (file.exists()){
            System.out.println("Файл присутствует");
            FileFound foundFile;
            foundFile = new FileFound();

        }
        else {
            System.out.println("Файла нет");
            FileNoFound noFoundFile = new FileNoFound();
            noFoundFile.no_found1();
        }
    }
}


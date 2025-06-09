package org.example;

import java.io.File;//импорт класса для чтения файла
import java.io.IOException;//импорт класса эксепшн


public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File("D:/test/test.txt");
        perspective_man man1 = new perspective_man();
        if (file.exists()){
            System.out.println("Файл присутствует");
            file_found found_file;
            found_file = new file_found();

            man1.salary = Long.parseLong(found_file.firstLine);
            man1.name = found_file.secondLine;
            man1.married = Boolean.parseBoolean(found_file.thirdLine);
            System.out.print("Данные инициализированы\n Зарплата:" + man1.salary + "\tИмя:" + man1.name + "\tЖенат ли:" + man1.married);
        }
        else {
            System.out.println("Файла нет");
            file_no_found no_found_file = new file_no_found();
            no_found_file.no_found1();
        }
    }
}


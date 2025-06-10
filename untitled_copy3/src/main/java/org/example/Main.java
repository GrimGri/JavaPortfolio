package org.example;

import java.io.File;//импорт класса для чтения файла
import java.io.IOException;//импорт класса эксепшн

public class Main {
    public static void main(String[] args) throws IOException {
        //final String adr = "D:/test/test.txt";
        File file = new File(GlobalVars.adr);//"D:/test/test.txt");
        PerspectiveMan man1 = new PerspectiveMan();
        if (file.exists()){
            System.out.println("Файл присутствует");
            FileFound found_file;
            found_file = new FileFound();

//            man1.salary = Long.parseLong(found_file.firstLine);
//            man1.name = found_file.secondLine;
//            man1.married = Boolean.parseBoolean(found_file.thirdLine);
//            System.out.print("Данные инициализированы\n Зарплата:" + man1.salary + "\tИмя:" + man1.name + "\tЖенат ли:" + man1.married);
        }
        else {
            System.out.println("Файла нет");
            FileNoFound no_found_file = new FileNoFound();
            no_found_file.no_found1();
        }
    }
}


package org.example;

import java.lang.System;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;//импорт класса эксепшн

class FileNoFound {
    void no_found1() {
        String filePath = GlobalVars.adr;//чтение файла

        Scanner scanner = new Scanner(System.in);

        String firstLine1 = new String();

        String secondLine1 = new String();

        String thirdLine1 = new String();
        try (FileWriter fileWriter = new FileWriter(filePath)) { // Создаем FileWriter с указанием пути к файлу
            System.out.println("Для создания файла введите зарплату: ");
            firstLine1 = scanner.nextLine();
            fileWriter.write(firstLine1 + "\n");
            System.out.print("Для создания файла введите имя: ");
            secondLine1 = scanner.nextLine();
            fileWriter.write(secondLine1 + "\n");
            System.out.print("Для создания файла введите женат ли: ");
            thirdLine1 = scanner.nextLine();
            fileWriter.write(thirdLine1 + "\n");
            
        } catch (IOException e) {
                e.printStackTrace(); // Обработка исключений при записи в файл
        } finally{
                scanner.close();
        }
        System.out.println("Данные успешно записаны в файл.");
    }
}
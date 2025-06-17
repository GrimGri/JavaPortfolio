package org.example;

import org.springframework.boot.SpringApplication;// Импорт главного класса для запуска спринг
import org.springframework.boot.autoconfigure.SpringBootApplication;// Импорт аннотации которая включает автоматическую настройку спринг

@SpringBootApplication// Основная аннотация для включения спринг
public class Main {
    public static void main(String[] args) {// Статик так как без создания экземпляра класса
            SpringApplication.run(Main.class, args);// Запуск спринг приложения.
        }
    }
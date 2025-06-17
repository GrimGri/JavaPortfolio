package org.example;

import jakarta.validation.constraints.NotBlank;// Импорт для валидации данных для строк
import jakarta.validation.constraints.NotNull;// Импорт для валидации данных для всех типов
import jakarta.validation.constraints.PositiveOrZero;// Импорт для валидации данных для чисел

public class PerspectiveManRequest {// Дто для входящих запросов

    @NotBlank// Строка не должна быть пустой или пробельной
    private String name;

    @PositiveOrZero// Значения больше нуля
    @NotNull// Запрет null
    private Long salary;

    @NotNull// Запрет null
    private Boolean married;

    // Геттер и сеттер имени
    public  String getName() {
    	return name;
    }
    public void setName(String name) {
	    this.name = name;
    }

    // Геттер и сеттер зарплаты
    public Long getSalary() {
        return salary;
    }
    public void setSalary(Long salary) {
        this.salary = salary;
    }

    // Геттер и сеттер семейного статуса
    public Boolean getMarried() {
        return married;
    }
    public void setMarried(Boolean married) {
        this.married = married;
    }
}

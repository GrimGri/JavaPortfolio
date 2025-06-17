package org.example;

public class PerspectiveManResponse {// Класс для исходящих ответов
    private Long id;// Ид сущности
    private String name;// Имя сущности
    private Long salary;// Зарплата сущности
    private Boolean married;// Семейное положение сущности

    // Геттер и сеттер ид
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    // Геттер и сеттер имени
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    // Геттер и сеттер зарплаты
    public Long getSalary() { return salary; }
    public void setSalary(Long salary) { this.salary = salary; }

    // Геттер и сеттер семейного положения
    public Boolean getMarried() { return married; }
    public void setMarried(Boolean married) { this.married = married; }
}

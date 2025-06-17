package org.example;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.*;// Подключение апи для работы с бд и валидации, аннотации гибернейт
import jakarta.validation.constraints.Min;// Подключение апи для работы с БД и валидации, аннотация проверки минимума
import jakarta.validation.constraints.NotBlank;// Подключение апи для работы с БД и валидации, аннотация проверки не пустого значения строки
import jakarta.validation.constraints.NotNull;// Подключение апи для работы с БД и валидации, аннотация проверки не нулевого значения
import jakarta.validation.constraints.Size;// Подключение апи для работы с БД и валидации, аннотация проверки размера


@Entity// Пометка класса для работы с бд
@Table(name = "perspective",// Конфигурация таблицы бд. "perspective" - имя таблицы
                uniqueConstraints = {// Уникальное ограничение по колонке
                        @UniqueConstraint(columnNames = "name")//"id"
                }
        )
public class PerspectiveMan {
    @Id// Пометка первичный ключ
    @GeneratedValue(strategy = GenerationType.IDENTITY)// Стратегия генерации ид по автоинкременту
    private Long id;

    @Column(nullable = false)// Запрет null в бд
    @NotBlank// Ограничение строка не пустая и не пробелы
    @Size(min = 2, max = 50)// Ограничение строка от 2 до 50 символов
    private String name;

    @Min(0)// Ограничение значение минимальное 0
    @NotNull// Ограничение не пустое
    private Long salary;

    @NotNull// Ограничение не пустое
    private Boolean married;

    // Геттер и сеттер ид
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    // Геттер и сеттер имени
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    // Геттер и сеттер зарплаты
    public Long getSalary() { return salary; }
    public void setSalary(Long salary) { this.salary = salary; }

    // Геттер и сеттер семейного статуса
    public Boolean getMarried() { return married; }
    public void setMarried(Boolean married) { this.married = married; }
}
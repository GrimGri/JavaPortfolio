package org.example;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import org.springframework.data.annotation.Id;

@Entity
@Table(name = "perspectiveMan")

public class PerspectiveMan {
    @jakarta.persistence.Id
    private static Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public static Long getId() {
        return id;
    }

    private static String name;
    public void setName(String name) {
        this.name = name;
    }
    public static String getName() {
        return name;
    }

    private static Long salary;
    public void setSalary(Long salary) {
        this.salary = salary;
    }
    public static Long getSalary() {
        return salary;
    }
    private static Boolean married;
    public void setMarried(Boolean married) {
        this.married = married;
    }
    public static Boolean getMarried() {
        return married;
    }
}
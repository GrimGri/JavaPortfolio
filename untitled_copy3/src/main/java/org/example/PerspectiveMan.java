package org.example;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.*;

@Entity
@Table(name = "perspectiveMan")

public class PerspectiveMan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    public void setId(Long id) {
//        this.id = id;
//    }

//    public static Long getId() {
//        return id;
//    }

    private String name;
//    public void setName(String name) {
//        this.name = name;
//    }
//    public static String getName() {
//        return name;
//    }

    private Long salary;
//    public void setSalary(Long salary) {
//        this.salary = salary;
//    }
//    public static Long getSalary() {
//        return salary;
//    }
    private Boolean married;
//    public void setMarried(Boolean married) {
//        this.married = married;
//    }
//    public static Boolean getMarried() {
//        return married;
//    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Long getSalary() { return salary; }
    public void setSalary(Long salary) { this.salary = salary; }
    public Boolean getMarried() { return married; }
    public void setMarried(Boolean married) { this.married = married; }
}
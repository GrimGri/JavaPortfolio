package org.example;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "perspective",
                uniqueConstraints = {
                        @UniqueConstraint(columnNames = "name")
                }
        )
@Data
public class PerspectiveMan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank
    @Size(min = 2, max = 50)
    private String name;

    @Min(0)
    private Long salary;

    @NotNull
    private Boolean married;

    //public Long getId() { return id; }
    //public void setId(Long id) { this.id = id; }

    //public String getName() { return name; }
    //public void setName(String name) { this.name = name; }

    //public Long getSalary() { return salary; }
    //public void setSalary(Long salary) { this.salary = salary; }

    //public Boolean getMarried() { return married; }
    //public void setMarried(Boolean married) { this.married = married; }
}
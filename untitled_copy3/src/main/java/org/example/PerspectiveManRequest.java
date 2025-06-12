package org.example;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class PerspectiveManRequest {
    @NotBlank
    private String name;

    @PositiveOrZero
    @NotNull
    private Long salary;

    @NotNull
    private Boolean married;

    public  String getName() {
    	return name;
    }
    public void setName(String name) {
	    this.name = name;
    }
    public Long getSalary() {
        return salary;
    }
    public void setSalary(Long salary) {
        this.salary = salary;
    }
    public Boolean getMarried() {
        return married;
    }
    public void setMarried(Boolean married) {
        this.married = married;
    }
}

package org.example;
//import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")

public class MainController {
    @GetMapping
    public List<PerspectiveMan> getAllData(){
        return DataService.getAll();
    }
    // Метод для получения данных по ID
    @GetMapping("/{id}")
    public PerspectiveMan getDataById(@PathVariable Long id) {
        // Логика для получения данных из БД по ID
        return DataService.getById(id);
    }
    // Метод для добавления новых данных
    @PostMapping
    public PerspectiveMan save(@RequestBody PerspectiveMan data) {
        // Логика для добавления данных в БД
        return DataService.save(data);
    }

    // Метод для обновления данных
    @PutMapping("/{id}")
    public PerspectiveMan update(@PathVariable Long id, @RequestBody PerspectiveMan data) {
        // Логика для обновления данных в БД
        return DataService.update(id, data);
    }

    // Метод для удаления данных
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        // Логика для удаления данных из БД
        DataService.delete(id);
    }
};

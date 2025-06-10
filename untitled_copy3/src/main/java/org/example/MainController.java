package org.example;
//import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/perspectiveMan")

public class MainController {
    @Autowired
    private PerspectiveManRepo perspectiveManRepo;
    @GetMapping
    public List<perspectiveManRepo> getAllPerspectiveMan() {
        List<perspectiveManRepo> all = PerspectiveManRepo.findAll();
        return all;
    }
    // Метод для получения данных по ID
    @GetMapping("/{id}")
    public PerspectiveMan getPerspectiveManById(@PathVariable Long id) {
        // Логика для получения данных из БД по ID
        return PerspectiveManRepo.findById(id).orElse(null);
    }
    // Метод для добавления новых данных
    @PostMapping
    public PerspectiveMan createPerspectiveMan(@RequestBody PerspectiveMan perspectiveMan) {
        // Логика для добавления данных в БД
        return PerspectiveManRepo.save(perspectiveMan);
    }

    // Метод для обновления данных
    @PutMapping("/{id}")
    public PerspectiveMan updatePerspectiveMan(@PathVariable Long id, @RequestBody PerspectiveMan perspectiveMan) {
        // Логика для обновления данных в БД
        perspectiveMan.setId(id);//
        return perspectiveManRepo.save(perspectiveMan);
    }

    // Метод для удаления данных
    @DeleteMapping("/{id}")
    public void deletePerspectiveMan(@PathVariable Long id) {
        // Логика для удаления данных из БД
        perspectiveManRepo.deleteById(id);
    }
};

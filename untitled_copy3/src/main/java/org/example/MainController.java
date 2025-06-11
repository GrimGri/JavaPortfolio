package org.example;
//import org.springframework.web.bind.annotation.*;

import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/perspectiveMan")
@Tag(name = "Perspective Man API", description = "Управление перспективными личностями")
public class MainController {
    @Autowired
    private PerspectiveManRepo service;

    @Operation(summary = "Получить всех кандидатов")
    @ApiResponse(responseCode = "200", description = "Список кандидатов")
    //static class MainControllerService {
        
    //}//perspectiveManRepo;
    @GetMapping("/{id}")
    public List<PerspectiveMan> getAll() {
        //return perspectiveManRepo.findAll();//perspectiveManRepo> all = PerspectiveManRepo.findAll();
        //return all;
        return service.getAll();
    }
    @Operation(summary = "Найти кандидата по ID")
    @ApiResponse(responseCode = "200", description = "Найденный кандидат")
    @ApiResponse(responseCode = "404", description = "Кандидат не найден")
    // Метод для получения данных по ID
    @GetMapping("/{id}")
    public PerspectiveMan getById(
            @Parameter(description = "ID кандидата", example = "1")
            @PathVariable Long id)
    {
            //@PathVariable Long id) {
        // Логика для получения данных из БД по ID
        //return perspectiveManRepo.findById(id).orElse(null);
        return service.getById(id);
    }
    // Метод для добавления новых данных
    @Operation(summary = "Добавить нового кандидата")
    @ApiResponse(responseCode = "201", description = "Кандидат создан")
    @PostMapping
    public ResponseEntity<PerspectiveMan> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Данные кандидата",
                    required = true,
                    content = @Content(schema = @Schema(implementation = PerspectiveMan.class))
            )//PerspectiveMan createPerspectiveMan(@RequestBody PerspectiveMan perspectiveMan) {
        // Логика для добавления данных в БД
        //return perspectiveManRepo.save(perspectiveMan);
    //}
            @RequestBody PerspectiveMan man
    ) {
        return ResponseEntity.status(201).body((PerspectiveMan) service.create(man));
    }

    @Operation(summary = "Обновить данные кандидата")
    @ApiResponse(responseCode = "200", description = "Кандидат обновлен")
    @ApiResponse(responseCode = "400", description = "Ошибка обновления")
    // Метод для обновления данных
    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePerspectiveMan(@PathVariable Long id, @RequestBody PerspectiveMan man) {
        // Логика для обновления данных в БД
        //        perspectiveMan.setId(id);//
        //return perspectiveManRepo.save(perspectiveMan);
        return ResponseEntity.status(200).body(service.update(man));
    }

    @Operation(summary = "Удаление кандидата")
    @ApiResponse(responseCode = "204", description = "Кандидат удален")
    @ApiResponse(responseCode = "400", description = "Неверный запрос")
    @ApiResponse(responseCode = "404", description = "Кандидат не найден")
    // Метод для удаления данных
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePerspectiveMan(@PathVariable Long id, @RequestBody PerspectiveMan man) {
        // Логика для удаления данных из БД
     //   perspectiveManRepo.deleteById(id);
        return ResponseEntity.status(204).body(service.delete(man));
    }
};

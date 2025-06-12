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
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/perspectiveMan")
@Tag(name = "Perspective Man API", description = "Управление перспективными личностями")
public class MainController {
    @Autowired
    private PerspectiveManService service;

    @GetMapping
    @Operation(summary = "Получить всех кандидатов")
    public List<PerspectiveMan> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Найти кандидата по ID")
    public ResponseEntity<PerspectiveMan> getById(
            @Parameter(description = "ID кандидата", example = "1")
            @PathVariable Long id)
    {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Метод для добавления новых данных
    @PostMapping
    @Operation(summary = "Добавить нового кандидата")
    public ResponseEntity<PerspectiveMan> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Данные кандидата",
                    required = true,
                    content = @Content(schema = @Schema(implementation = PerspectiveMan.class))
            )
            @RequestBody PerspectiveMan man)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(man));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить данные кандидата")
    // Метод для обновления данных
    public ResponseEntity<Object> update(
            @PathVariable Long id,
            @RequestBody PerspectiveMan manDetails) {
        try {
            return ResponseEntity.ok(service.update(id, manDetails));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление кандидата")
    // Метод для удаления данных
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
};

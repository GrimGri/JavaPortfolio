package org.example;
//import org.springframework.web.bind.annotation.*;

import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.hibernate.query.sqm.EntityTypeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
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

    //Вспомогательный метод
    private PerspectiveMan convertToEntity(PerspectiveManRequest request) {
        PerspectiveMan entity = new PerspectiveMan();
        entity.setName(request.getName());
        entity.setSalary(request.getSalary());
        entity.setMarried(request.getMarried());
        return entity;
    }
    @GetMapping
    @Operation(summary = "Получить всех личностей")
    public List<PerspectiveMan> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Найти личность по ID")
    public ResponseEntity<PerspectiveMan> getById(
            @Parameter(description = "ID личности", example = "1")
            @PathVariable Long id)
    {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Метод для добавления новых данных
    @PostMapping
    @Operation(summary = "Добавить новую личность")
    public ResponseEntity<PerspectiveMan> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Данные личности",
                    required = true,
                    content = @Content(schema = @Schema(implementation = PerspectiveManRequest.class))
            )
            @Valid
            @RequestBody PerspectiveManRequest request)
    {
        PerspectiveMan man = convertToEntity(request);//new PerspectiveMan();
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(man));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить данные личности")
    // Метод для обновления данных
    public ResponseEntity<PerspectiveMan> update(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Данные для обновления",
                    required = true,
                    content = @Content(schema = @Schema(implementation = PerspectiveManRequest.class)))
            @PathVariable Long id,
            @RequestBody PerspectiveManRequest request) {
        PerspectiveMan updateTemplate = convertToEntity(request);//new PerspectiveMan();
        return ResponseEntity.ok(service.update(id, updateTemplate));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление личности")
    // Метод для удаления данных
    public ResponseEntity<Void> delete(@PathVariable Long id) {
    try {
        service.delete(id);
        return ResponseEntity.noContent().build();
    } catch (EntityNotFoundException e) {
        return ResponseEntity.notFound().build();
        }
    }

};

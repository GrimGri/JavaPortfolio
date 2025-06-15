package org.example;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/perspectiveMan")
@Tag(name = "Perspective Man API", description = "Управление перспективными личностями")
public class MainController {
    private final PerspectiveManService service;
    private final PerspectiveManMapper mapper;
    @Autowired
    public MainController(
            PerspectiveManService service,
            PerspectiveManMapper mapper
    ) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    @Operation(summary = "Получить всех личностей")
    public Page<PerspectiveMan> getAll(Pageable pageable) {
        return service.getAll(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Найти личность по ID")
    public ResponseEntity<PerspectiveMan> getById(
             @PathVariable Long id)
    {
        try {
            return ResponseEntity.ok(service.getById(id));
        } catch (EntityNotFoundException e)
        {
            return ResponseEntity.notFound().build();
        }
    }

    // Метод для добавления новых данных
    @PostMapping
    @Operation(summary = "Добавить новую личность")
    public ResponseEntity<PerspectiveMan> create(
            @Valid
            @RequestBody PerspectiveManRequest request)
    {
        PerspectiveMan man =  mapper.toEntity(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(man));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить данные личности")
    // Метод для обновления данных
    public ResponseEntity<PerspectiveMan> update(
           @PathVariable Long id,
           @RequestBody PerspectiveManRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление личности")
    // Метод для удаления данных
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

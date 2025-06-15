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
    public Page<PerspectiveManResponse> getAll(Pageable pageable) {
        return service.getAll(pageable).map(mapper::toResponse);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Найти личность по ID")
    public ResponseEntity<PerspectiveManResponse> getById(
             @PathVariable Long id)
    {
        try {
            PerspectiveMan entity = service.getById(id);
            //return ResponseEntity.ok(service.getById(id));//mapper.toResponse(entity));//service.getById(id));
            PerspectiveManResponse response = mapper.toResponse(entity); // Используем маппер
            return ResponseEntity.ok(response);
        } catch (EntityNotFoundException e)
        {
            return ResponseEntity.notFound().build();
        }
    }

    // Метод для добавления новых данных
    @PostMapping
    @Operation(summary = "Добавить новую личность")
    //public ResponseEntity<PerspectiveMan> create(
    public ResponseEntity<PerspectiveManResponse> create(

            @Valid
            @RequestBody PerspectiveManRequest request)
    {
    //    PerspectiveMan man =  mapper.toEntity(request);
        PerspectiveMan entity =  mapper.toEntity(request);
        PerspectiveMan created = service.create(entity);
        //return ResponseEntity.status(HttpStatus.CREATED).body(service.create(man));//
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(created));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить данные личности")
    // Метод для обновления данных
    public ResponseEntity<PerspectiveManResponse> update(
           @PathVariable Long id,
           @RequestBody PerspectiveManRequest request) {
        PerspectiveMan updated = service.update(id, request);
        return ResponseEntity.ok(mapper.toResponse(updated));//service.update(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление личности")
    // Метод для удаления данных
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

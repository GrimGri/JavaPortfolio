package org.example;

import io.swagger.v3.oas.annotations.Operation;// Импорт аннотации Сваггер для документирования апи, описывает отдельные эндпоинты
import io.swagger.v3.oas.annotations.tags.Tag;// Импорт аннотаций Сваггер для документирования апи, группирует связанные эндпоинты в документации
import jakarta.persistence.EntityNotFoundException;// Импорт исключения, возникающего при отсутствии сущности в БД
import jakarta.validation.Valid;// Импорт аннотации для валидации входящих данных
import org.springframework.beans.factory.annotation.Autowired;// Импорт аннотации для внедрения зависимостей
import org.springframework.data.domain.Page;// Импорт результата запроса с данными и метаданными
import org.springframework.data.domain.Pageable;// Импорт интерфейса для параметров пагинации
import org.springframework.http.ResponseEntity;// Импорт обертки для управления ответом
import org.springframework.web.bind.annotation.*;// Импорт аннотаций Спринг мвц для объявления рест контроллера и обработки HTTP запросов
import org.springframework.http.HttpStatus;// Импорт константы HTTP статусов

@RestController// Объявляет класс как рест контроллер. Автоматически сериализует возвращаемые объекты
@RequestMapping("/api/perspectiveMan")//Задает базовый путь для всех эндпоинтов контроллера
@Tag(name = "Perspective Man API", description = "Управление перспективными личностями")// Добавляет метаданные для Сваггер. Группирует все эндпоинты контроллера под тегом в документации.
public class MainController {// Объявление класса контроллера
    private final PerspectiveManService service;// Зависимость контроллера, содержит бизнес-логику работы с БД
    private final PerspectiveManMapper mapper;// Зависимость контроллера, преобразует запрос/ответ в сущности и обратно
    @Autowired// Конструктор с внедрением зависимостей. Спринг автоматически передает реализации service и mapper.
    public MainController(
            PerspectiveManService service,
            PerspectiveManMapper mapper
    ) {
        this.service = service;
        this.mapper = mapper;
    }

    // Эндпоинт для получения всех записей с пагинацией
    @GetMapping// Обработка гет запросов
    @Operation(summary = "Получить всех личностей")
    // Pageable параметры пагинации из запроса
    public Page<PerspectiveManResponse> getAll(Pageable pageable) {
        return service.getAll(pageable).map(mapper::toResponse);// Возвращает страницу сущностей. Конвертирует каждую сущность в ман респонс
    }

    //Эндпоинт для поиска по ID
    @GetMapping("/{id}")
    @Operation(summary = "Найти личность по ID")
    public ResponseEntity<PerspectiveManResponse> getById(
             @PathVariable Long id)// Извлекает ид из URL
    {
        try {
            PerspectiveMan entity = service.getById(id);
            PerspectiveManResponse response = mapper.toResponse(entity); // Используем маппер
            return ResponseEntity.ok(response);
        } catch (EntityNotFoundException e)// Обработка ошибок если сущность не найдена
        {
            return ResponseEntity.notFound().build();
        }
    }

    // Метод для добавления новых данных
    @PostMapping // Обрабатывает пост запросы
    @Operation(summary = "Добавить новую личность")
    //public ResponseEntity<PerspectiveMan> create(
    public ResponseEntity<PerspectiveManResponse> create(
            @Valid// Активирует валидацию полей в манреквесте
            @RequestBody PerspectiveManRequest request)// Десериализует JSON из тела запроса
    {

        PerspectiveMan entity =  mapper.toEntity(request);
        PerspectiveMan created = service.create(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(created));//Возвращает созданную сущность со статусом 201 Created
    }

    //Эндпоинт для обновления записи
    @PutMapping("/{id}")// Обрабатывает PUT-запросы
    @Operation(summary = "Обновить данные личности")
    // Метод для обновления данных
    public ResponseEntity<PerspectiveManResponse> update(
           @PathVariable Long id,
           @RequestBody PerspectiveManRequest request) {
        PerspectiveMan updated = service.update(id, request);// Обновляет сущность с указанным ид данными из реквест
        return ResponseEntity.ok(mapper.toResponse(updated));// Возвращает обновленную сущность со статусом 200 OK
    }

    // Эндпоинт для удаления записи.
    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление личности")// Обрабатывает делет запросы
    // Метод для удаления данных
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);// Удаляет сущность из БД
        return ResponseEntity.noContent().build();// Возвращает статус 204 успешное удаление
    }
}

package org.example;//Объявление пакета которому принадлежит класс

import java.util.Map;//Для работы с коллекциями
import java.util.HashMap;//Для работы с хэшколлекциями
import jakarta.persistence.EntityNotFoundException;//Исключение нет значений
import org.springframework.dao.EmptyResultDataAccessException;//Исключение нет пути
import org.springframework.http.HttpStatus;//Статус http запроса
import org.springframework.http.ResponseEntity;//
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;//Аннотации
import org.springframework.web.bind.annotation.ExceptionHandler;//Аннотации

@ControllerAdvice//Пометка класса как глобальнго обработчика исключений обрабатываемый Spring
public class GlobalExceptionHandler {//Объявление класса-обработчика исключений, public для доступа Spring

    @ExceptionHandler(MethodArgumentNotValidException.class)//Обработчик ошибок валидации
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();//Создание коллекции для хранения ошибок

        // Обработка ошибок полей
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            //ex.getBindingResult() получает результаты валидации
            //getFieldErrors() возвращает ошибки полей
            errors.put(error.getField(), error.getDefaultMessage());
        }

        // Обработка глобальных ошибок (не привязанных к полю)
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.put("global", error.getDefaultMessage());
            //global унифицированный ключ для ошибок уровня объекта
        }
        return ResponseEntity.badRequest().body(errors);//Формирование ответа
    }

    // Обработка отсутствия сущности
    @ExceptionHandler(EntityNotFoundException.class)//Обработка случаев "сущность не найдена"
    public ResponseEntity<String> handleEntityNotFound(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    // Общий обработчик исключений
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAllExceptions(Exception ex) {
        return ResponseEntity.internalServerError()
                .body("Internal server error: " + ex.getMessage());
    }

    // Обработка пустых результатов
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<String> handleEmptyResult(EmptyResultDataAccessException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Requested resource not found");
        // Возвращает унифицированное сообщение вместо технических деталей
        // Чтобы не раскрывать внутреннюю структуру БД
    }
}

package org.example;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;// Импорт управления транзакциями
import org.slf4j.Logger;// Импорт для логгирования
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;// Импорт для пагинации
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;// Импорт для внедрения зависимостей
import org.springframework.stereotype.Service;// Импорт для маркировки спринг бина

@Service// Метка класса как сервисного компонента спринга

@Transactional// Транзакции на уровне класса
public class PerspectiveManService {
    private static final Logger log = LoggerFactory.getLogger(PerspectiveManService.class);// Инициализация логгера
    private final PerspectiveManRepo repository;// Установление зависимостей финал гарантирует инициализацию в конструкторе
    private final PerspectiveManMapper mapper;
    @Autowired// Конструктор внедрения зависимостей
    public PerspectiveManService(
            PerspectiveManRepo repository,
            PerspectiveManMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    // Получение данных пагинацией
    public Page<PerspectiveMan> getAll(Pageable pageable) {
        log.debug("Fetching all perspective men with pagination");// Логгирование
        return repository.findAll(pageable);
    }

    // Поиск сущности по ид
    public PerspectiveMan getById(Long id){
        log.debug("Fetching perspective man by ID: {}", id);
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "PerspectiveMan not found with id: " + id
                ));
    }

    // Создание новой сущности
    public PerspectiveMan create(PerspectiveMan man) {
        log.info("Creating new perspective man: {}", man.getName());
        if (man.getId() != null) {
            throw new IllegalStateException("New entity must not have id");
        }
        return repository.save(man);
    }

    // Обновление сущности по ид
    public PerspectiveMan update(Long id, PerspectiveManRequest request){
        PerspectiveMan entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("PerspectiveMan not found with id: " + id));
        mapper.updateFromRequest(request, entity);
        return repository.save(entity); // Сохраняем изменения
        //return entity;
    }

    // Удаление сущности по ид
    public void delete(Long id) {
        log.warn("Deleting perspective man with ID: {}", id);
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new EntityNotFoundException(
                    "PerspectiveMan not found with id: " + id
            );
        }
    }
}


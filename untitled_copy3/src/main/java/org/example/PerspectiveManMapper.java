package org.example;

import org.mapstruct.Mapper;// Импорт основной аннотации для интерфейсов маппера
import org.mapstruct.MappingTarget;// Импорт маркера для целевого обновления объекта
import org.mapstruct.Mapping;// Импорт аннотации для своих правил преобразования

// Создать маппер
@Mapper(componentModel = "spring")// Конфигурация мапера для интеграции с спрингом
public interface PerspectiveManMapper {// Объявление интерфейса мапера

    @Mapping(target = "id", ignore = true)// Указание игнорирования поля ид при копировании
    void updateFromRequest(// Обновление сущности данными дто
            PerspectiveManRequest request,// Источник новых данных
            @MappingTarget PerspectiveMan entity// Цель куда обновлять
    );
    PerspectiveMan toEntity(PerspectiveManRequest request);// Создание новой сущности
    PerspectiveManResponse toResponse(PerspectiveMan entity);// Преобразование сущности в дто для отправки клиенту
}

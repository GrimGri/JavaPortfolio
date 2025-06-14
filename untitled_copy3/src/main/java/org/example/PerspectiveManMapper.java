package org.example;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mapping;

// Создать маппер
@Mapper(componentModel = "spring")
public interface PerspectiveManMapper {
    PerspectiveMan toEntity(PerspectiveManRequest request);

    @Mapping(target = "id", ignore = true)
    void updateFromRequest(
            PerspectiveManRequest request,
            @MappingTarget PerspectiveMan entity
    );
    PerspectiveManResponse toResponse(PerspectiveMan entity);
}

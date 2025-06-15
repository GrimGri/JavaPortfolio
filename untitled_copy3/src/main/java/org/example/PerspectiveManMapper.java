package org.example;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

// Создать маппер
@Mapper(componentModel = "spring")
public interface PerspectiveManMapper {
    PerspectiveMan toEntity(PerspectiveManRequest request);

    @Mapping(target = "id", ignore = true)
    void updateFromRequest(
            PerspectiveManRequest request,
            @MappingTarget PerspectiveMan entity
    );
    //Page<PerspectiveManResponse> toResponsePage(Page<PerspectiveMan> page);
    PerspectiveManResponse toResponse(PerspectiveMan entity);
}

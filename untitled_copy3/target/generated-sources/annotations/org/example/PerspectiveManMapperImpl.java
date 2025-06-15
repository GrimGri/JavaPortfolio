package org.example;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-15T09:59:35+0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 24.0.1 (Oracle Corporation)"
)
@Component
public class PerspectiveManMapperImpl implements PerspectiveManMapper {

    @Override
    public PerspectiveMan toEntity(PerspectiveManRequest request) {
        if ( request == null ) {
            return null;
        }

        PerspectiveMan perspectiveMan = new PerspectiveMan();

        perspectiveMan.setName( request.getName() );
        perspectiveMan.setSalary( request.getSalary() );
        perspectiveMan.setMarried( request.getMarried() );

        return perspectiveMan;
    }

    @Override
    public void updateFromRequest(PerspectiveManRequest request, PerspectiveMan entity) {
        if ( request == null ) {
            return;
        }

        entity.setName( request.getName() );
        entity.setSalary( request.getSalary() );
        entity.setMarried( request.getMarried() );
    }

    @Override
    public PerspectiveManResponse toResponse(PerspectiveMan entity) {
        if ( entity == null ) {
            return null;
        }

        PerspectiveManResponse perspectiveManResponse = new PerspectiveManResponse();

        perspectiveManResponse.setId( entity.getId() );
        perspectiveManResponse.setName( entity.getName() );
        perspectiveManResponse.setSalary( entity.getSalary() );
        perspectiveManResponse.setMarried( entity.getMarried() );

        return perspectiveManResponse;
    }
}

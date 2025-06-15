package org.example;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

@Transactional
public class PerspectiveManService {
    private static final Logger log = LoggerFactory.getLogger(PerspectiveManService.class);
    private final PerspectiveManRepo repository;
    private final PerspectiveManMapper mapper;
    @Autowired
    public PerspectiveManService(
            PerspectiveManRepo repository,
            PerspectiveManMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Page<PerspectiveMan> getAll(Pageable pageable) {
        log.debug("Fetching all perspective men with pagination");
        return repository.findAll(pageable);
    }

    public PerspectiveMan getById(Long id){
        log.debug("Fetching perspective man by ID: {}", id);
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "PerspectiveMan not found with id: " + id
                ));
    }

    public PerspectiveMan create(PerspectiveMan man) {
        log.info("Creating new perspective man: {}", man.getName());
        if (man.getId() != null) {
            throw new IllegalStateException("New entity must not have id");
        }
        return repository.save(man);
    }
    public PerspectiveMan update(Long id, PerspectiveManRequest request){
        PerspectiveMan entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("PerspectiveMan not found with id: " + id));
        mapper.updateFromRequest(request, entity);
        return repository.save(entity); // Сохраняем изменения
        //return entity;
    }

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


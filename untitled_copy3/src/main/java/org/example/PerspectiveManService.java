package org.example;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PerspectiveManService {
    @Autowired
    private PerspectiveManRepo repository;

    public List<PerspectiveMan> getAll() {
        return repository.findAll();
    }

    public Optional<PerspectiveMan> getById(Long id) {
        return repository.findById(id);
    }

    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    public PerspectiveMan create(PerspectiveMan man) {
        log.info("Creating new perspective man: {}", man.getName());
        return repository.save(man);
    }
    public ResponseEntity<PerspectiveMan> update(Long id, PerspectiveMan manDetails) {
        PerspectiveMan man = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("PerspectiveMan not found with id: " + id));
        man.setName(manDetails.getName());
        man.setSalary(manDetails.getSalary());
        man.setMarried(manDetails.getMarried());
        return repository.save(man);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}


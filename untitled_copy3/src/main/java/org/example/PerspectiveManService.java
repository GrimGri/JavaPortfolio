package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PerspectiveManService {
    @Autowired
    private PerspectiveManRepo repository;

    public List<PerspectiveMan> getAll() {
        return repository.findAll();
    }

    public Optional<PerspectiveMan> getById(Long id) {
        return repository.findById(id);
    }

    public PerspectiveMan create(PerspectiveMan man) {
        return repository.save(man);
    }
    public PerspectiveMan update(Long id, PerspectiveMan manDetails) {
        PerspectiveMan man = repository.findById(id).orElseThrow();
        man.setName(manDetails.getName());
        man.setSalary(manDetails.getSalary());
        man.setMarried(manDetails.getMarried());
        return repository.save(man);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}


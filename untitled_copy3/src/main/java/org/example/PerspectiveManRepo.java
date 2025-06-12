package org.example;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
//import java.util.List;

public interface PerspectiveManRepo extends JpaRepository<PerspectiveMan, Long> {
    List<PerspectiveMan> findAll();
    Object save(PerspectiveMan man);
//    Object update(PerspectiveMan man);
    void delete(PerspectiveMan man);
}

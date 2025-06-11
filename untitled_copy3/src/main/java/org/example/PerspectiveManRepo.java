package org.example;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PerspectiveManRepo extends JpaRepository<PerspectiveMan, Long> {
    List<PerspectiveMan> getAll();
    Object create(PerspectiveMan man);
    Object update(PerspectiveMan man);
    //Object delete(PerspectiveMan man);
}
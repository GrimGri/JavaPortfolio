package org.example;

import org.springframework.data.jpa.repository.JpaRepository;// Импорт ключевого интерфейса для работ ы с бд

public interface PerspectiveManRepo extends JpaRepository<PerspectiveMan, Long> {// Объявление репозитория для работы с сущностью perspectiveman
    //PerspectiveMan тип управляемой сущности
    //Long тип первичного ключа
}

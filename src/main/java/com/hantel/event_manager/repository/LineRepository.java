package com.hantel.event_manager.repository;

import com.hantel.event_manager.entity.hall.Line;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class LineRepository {
    private final EntityManager entityManager;

    @Autowired
    public LineRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Line findById(Long id) {
        return entityManager.find(Line.class, id);
    }
}

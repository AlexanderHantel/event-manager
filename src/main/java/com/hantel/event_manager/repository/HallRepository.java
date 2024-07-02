package com.hantel.event_manager.repository;

import com.hantel.event_manager.entity.hall.Hall;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class HallRepository {
    public static final String FIND_ALL = "from Hall";

    private final EntityManager entityManager;

    @Autowired
    public HallRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Hall findById(Long id) {
        return entityManager.find(Hall.class, id);
    }

    public List<Hall> findAll() {
        return entityManager.createQuery(FIND_ALL, Hall.class).getResultList();
    }
}

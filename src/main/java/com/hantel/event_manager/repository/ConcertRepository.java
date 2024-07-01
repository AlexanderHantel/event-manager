package com.hantel.event_manager.repository;

import com.hantel.event_manager.entity.Concert;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class ConcertRepository {
    private final EntityManager entityManager;
    public static final String FIND_ALL_BY_MUSICAL_ID = "select m from Concert m where m.musicalId=:musicalId";

    @Autowired
    public ConcertRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Concert> findAllByMusicalId(Long musicalId) {
        TypedQuery<Concert> namedQuery = entityManager.createQuery(FIND_ALL_BY_MUSICAL_ID, Concert.class);
        return namedQuery.getResultList();
    }
}

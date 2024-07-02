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
    public static final String FIND_ALL = "from Concert";
    public static final String FIND_ALL_BY_MUSICAL_ID = "select c from Concert c where c.musicalId=:musicalId";

    @Autowired
    public ConcertRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Concert> findAllByMusicalId(Long musicalId) {
        TypedQuery<Concert> namedQuery = entityManager.createQuery(FIND_ALL_BY_MUSICAL_ID, Concert.class);
        return namedQuery.getResultList();
    }

    public List<Concert> findAll() {
        return entityManager.createQuery(FIND_ALL, Concert.class).getResultList();
    }

    public void save(Concert concert){
        entityManager.persist(concert);
    }
}

package com.hantel.event_manager.repository;

import com.hantel.event_manager.entity.hall.Hall;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class HallRepository {
    public static final String FIND_ALL = "from Hall";
    public static final String GET_SEATS_AMOUNT = "select SUM(l.seatsPerLine) from Line l where l.hall.id = :hallId";

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

    public int getFreeSeatsNumber(Long concertId, Long musicalId) {
        return 0;
    }

    public int getSeatsAmount(Long hallId) {
        Query query = entityManager.createQuery(GET_SEATS_AMOUNT);
        query.setParameter("hallId", hallId);
        Long countResult = (Long) query.getSingleResult();
        return countResult.intValue();
    }
}

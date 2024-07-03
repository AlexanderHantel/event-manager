package com.hantel.event_manager.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class BookedSeatRepository {
    private final EntityManager entityManager;
    public static final String COUNT_BOOKED_SEATS =
            "select count(bs) from BookedSeat bs where bs.concert.id = :concertId";

    @Autowired
    public BookedSeatRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public int getBookedSeatsAmountByConcertId(Long concertId) {
        Query query = entityManager.createQuery(COUNT_BOOKED_SEATS);
        query.setParameter("concertId", concertId);
        Long cuntResult = (Long) query.getSingleResult();
        return cuntResult.intValue();
    }
}

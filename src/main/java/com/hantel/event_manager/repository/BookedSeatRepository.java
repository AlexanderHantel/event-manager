package com.hantel.event_manager.repository;

import com.hantel.event_manager.entity.hall.BookedSeat;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class BookedSeatRepository {
    private final EntityManager entityManager;
    private static final String COUNT_BOOKED_SEATS =
            "select count(bs) from BookedSeat bs where bs.concert.id = :concertId";
    private static final String FIND_ALL_BY_LINE_ID = "select s from BookedSeat s where s.line.id = :lineId";

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

    public List<BookedSeat> findAllByLineId(Long lineId) {
        TypedQuery<BookedSeat> typedQuery = entityManager.createQuery(FIND_ALL_BY_LINE_ID, BookedSeat.class)
                .setParameter("lineId", lineId);
        return typedQuery.getResultList();
    }
}

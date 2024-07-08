package com.hantel.event_manager.repository;

import com.hantel.event_manager.entity.hall.BookedSeat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BookedSeatRepositoryTest {
    @Autowired
    private BookedSeatRepository bookedSeatRepository;

    @Test
    void getBookedSeatsAmountByConcertId_IfConcert1() {
        Long concertId = 1L;
        int actualBookedSeatsAmount = bookedSeatRepository.getBookedSeatsAmountByConcertId(concertId);
        int expectedBookedSeatsAmount = 33;
        assertEquals(expectedBookedSeatsAmount, actualBookedSeatsAmount);
    }

    @Test
    void getBookedSeatsAmountByConcertId_IfConcert3_OneSeatIsUnbooked() {
        Long concertId = 3L;
        int actualBookedSeatsAmount = bookedSeatRepository.getBookedSeatsAmountByConcertId(concertId);
        int expectedBookedSeatsAmount = 57;
        assertEquals(expectedBookedSeatsAmount, actualBookedSeatsAmount);
    }

    @Test
    void getBookedSeatsAmountByConcertId_IfConcert3_SoldOut() {
        Long concertId = 4L;
        int actualBookedSeatsAmount = bookedSeatRepository.getBookedSeatsAmountByConcertId(concertId);
        int expectedBookedSeatsAmount = 58;
        assertEquals(expectedBookedSeatsAmount, actualBookedSeatsAmount);
    }

    @Test
    void findAllByLineIdAndConcertId_IfConcert1AndLine1() {
        Long lineId = 1L;
        Long concertId = 1L;
        int expectedBookedSeatsAmount = 3;
        List<BookedSeat> allByLineId = bookedSeatRepository.findAllByLineIdAndConcertId(lineId, concertId);
        assertEquals(expectedBookedSeatsAmount, allByLineId.size());
    }

    @Test
    void findAllByLineIdAndConcertId_IfConcert1AndLine3_SoldOut() {
        Long lineId = 3L;
        Long concertId = 1L;
        int expectedBookedSeatsAmount = 12;
        List<BookedSeat> allByLineId = bookedSeatRepository.findAllByLineIdAndConcertId(lineId, concertId);
        assertEquals(expectedBookedSeatsAmount, allByLineId.size());
    }
}
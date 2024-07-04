package com.hantel.event_manager.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookingServiceTest {
    @Autowired
    private BookingService bookingService;

    @Test
    @Transactional
    public void testGetHallOccupancy() {
        long hallId = 1L;
        long concertId = 1L;

        String expectedHallOccupancy = """
                  Seat:  1  2  3  4  5  6  7  8  9 10 11 12 13 14
                Row  1: -- XX -- -- -- -- -- -- -- --
                Row  2: -- -- -- -- XX XX -- -- -- --
                Row  3: -- -- -- -- -- -- -- -- -- -- -- --
                Row  4: -- XX -- -- -- -- -- -- -- -- -- --
                Row  5: -- -- -- -- -- -- -- -- -- -- -- -- -- --""";

        assertEquals(expectedHallOccupancy, bookingService.getHallOccupancy(hallId, concertId));
    }
}
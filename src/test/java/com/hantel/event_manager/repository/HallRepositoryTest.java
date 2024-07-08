package com.hantel.event_manager.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class HallRepositoryTest {
    @Autowired
    private HallRepository hallRepository;

    @Test
    public void getSeatsAmount_IfHallA() {
        Long hallId = 1L;
        int expectedSeatsAmount = 58;
        int actualSeatsAmount = hallRepository.getSeatsAmount(hallId);
        assertEquals(expectedSeatsAmount, actualSeatsAmount);
    }
}
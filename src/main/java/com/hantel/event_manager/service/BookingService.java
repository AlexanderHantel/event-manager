package com.hantel.event_manager.service;

import com.hantel.event_manager.repository.BookedSeatRepository;
import com.hantel.event_manager.repository.HallRepository;
import org.springframework.stereotype.Component;

@Component
public class BookingService {
    private final BookedSeatRepository bookedSeatRepository;
    private final HallRepository hallRepository;

    public BookingService(BookedSeatRepository bookedSeatRepository,
                          HallRepository hallRepository) {
        this.bookedSeatRepository = bookedSeatRepository;
        this.hallRepository = hallRepository;
    }

    public int getVacantSeatsAmount(Long concertId, Long hallId) {
        int bookedSeatsAmount = bookedSeatRepository.getBookedSeatsAmountByConcertId(concertId);
        int seatsAmount = hallRepository.getSeatsAmount(hallId);
        return seatsAmount - bookedSeatsAmount;
    }
}

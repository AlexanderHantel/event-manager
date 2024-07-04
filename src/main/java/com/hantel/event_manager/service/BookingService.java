package com.hantel.event_manager.service;

import com.hantel.event_manager.entity.Concert;
import com.hantel.event_manager.entity.hall.Hall;
import com.hantel.event_manager.repository.BookedSeatRepository;
import com.hantel.event_manager.repository.ConcertRepository;
import com.hantel.event_manager.repository.HallRepository;
import org.springframework.stereotype.Component;

@Component
public class BookingService {
    private final BookedSeatRepository bookedSeatRepository;
    private final HallRepository hallRepository;
    private final ConcertRepository concertRepository;
    private final HallOccupancyPrinter hallOccupancyPrinter;

    public BookingService(BookedSeatRepository bookedSeatRepository,
                          HallRepository hallRepository,
                          ConcertRepository concertRepository,
                          HallOccupancyPrinter hallOccupancyPrinter) {
        this.bookedSeatRepository = bookedSeatRepository;
        this.hallRepository = hallRepository;
        this.concertRepository = concertRepository;
        this.hallOccupancyPrinter = hallOccupancyPrinter;
    }

    public String getVacantSeatsAmount(Long concertId, Long hallId) {
        int bookedSeatsAmount = bookedSeatRepository.getBookedSeatsAmountByConcertId(concertId);
        int seatsAmount = hallRepository.getSeatsAmount(hallId);
        int vacantSeatsAmount = seatsAmount - bookedSeatsAmount;
        if (seatsAmount == 0) {
            return "SOLD OUT";
        }

        return String.valueOf(vacantSeatsAmount);
    }

    public String getHallOccupancy(Long hallId, Long concertId) {
        Hall hall = hallRepository.findById(hallId);
        Concert concert = concertRepository.findById(concertId);

        return hallOccupancyPrinter.getHallOccupancyTable(hall.getLines(), concert.getBookedSeats());
    }
}

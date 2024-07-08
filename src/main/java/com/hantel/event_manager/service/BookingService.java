package com.hantel.event_manager.service;

import com.hantel.event_manager.dto.LineTicketControllerDTO;
import com.hantel.event_manager.entity.Concert;
import com.hantel.event_manager.entity.hall.BookedSeat;
import com.hantel.event_manager.entity.hall.Hall;
import com.hantel.event_manager.entity.hall.Line;
import com.hantel.event_manager.repository.BookedSeatRepository;
import com.hantel.event_manager.repository.ConcertRepository;
import com.hantel.event_manager.repository.HallRepository;
import com.hantel.event_manager.repository.LineRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookingService {
    private final BookedSeatRepository bookedSeatRepository;
    private final HallRepository hallRepository;
    private final ConcertRepository concertRepository;
    private final HallLayoutPrinter hallLayoutPrinter;
    private final LineRepository lineRepository;

    public BookingService(BookedSeatRepository bookedSeatRepository,
                          HallRepository hallRepository,
                          ConcertRepository concertRepository,
                          HallLayoutPrinter hallLayoutPrinter,
                          LineRepository lineRepository) {
        this.bookedSeatRepository = bookedSeatRepository;
        this.hallRepository = hallRepository;
        this.concertRepository = concertRepository;
        this.hallLayoutPrinter = hallLayoutPrinter;
        this.lineRepository = lineRepository;
    }

    public BookedSeat save(BookedSeat bookedSeat) {
        return bookedSeatRepository.save(bookedSeat);
    }

    public String getVacantSeatsAmount(Long concertId, Long hallId) {
        int bookedSeatsAmount = bookedSeatRepository.getBookedSeatsAmountByConcertId(concertId);
        int seatsAmount = hallRepository.getSeatsAmount(hallId);
        int vacantSeatsAmount = seatsAmount - bookedSeatsAmount;
        if (vacantSeatsAmount == 0) {
            return "SOLD OUT";
        }

        return String.valueOf(vacantSeatsAmount);
    }

    public String getHallLayout(Long hallId, Long concertId) {
        return getHallLayout(hallId, concertId, new ArrayList<>());
    }

    public String getHallLayout(Long hallId, Long concertId, List<BookedSeat> customerBookedSeats) {
        Hall hall = hallRepository.findById(hallId);
        return hallLayoutPrinter.getHallLayoutTable(hall.getLines(), concertId, customerBookedSeats);
    }

    public List<LineTicketControllerDTO> getVacantLines(Long concertId) {
        List<LineTicketControllerDTO> vacantLines = new ArrayList<>();
        Concert concert = concertRepository.findById(concertId);
        List<Line> lines = concert.getHall().getLines();
        List<BookedSeat> bookedSeats = concert.getBookedSeats();

        for (Line line : lines) {
            int seatsPerLine = line.getSeatsPerLine();
            List<BookedSeat> bookedSeatsInLine = bookedSeats.stream()
                    .filter(bookedSeat -> bookedSeat.getLine().getOrdinalNumber() == line.getOrdinalNumber())
                    .toList();
            if (seatsPerLine != bookedSeatsInLine.size()) {
                vacantLines.add(new LineTicketControllerDTO(line.getId(), line.getOrdinalNumber()));
            }
        }

        return vacantLines;
    }

    public List<Integer> getVacantSeatsForLine(Long lineId, Long concertId) {
        Line line = lineRepository.findById(lineId);
        int seatsPerLine = line.getSeatsPerLine();

        List<Integer> vacantSeats = new ArrayList<>();
        for (int i = 0; i < seatsPerLine; i++) {
            vacantSeats.add(i + 1);
        }

        List<BookedSeat> bookedSeats = bookedSeatRepository.findAllByLineIdAndConcertId(lineId, concertId);

        List<Integer> bookedSeatsOrdinalNumbers = bookedSeats.stream()
                .map(BookedSeat::getSeatOrdinalNumber)
                .toList();

        for (Integer bookedSeat : bookedSeatsOrdinalNumbers) {
            vacantSeats.remove(bookedSeat);
        }

        return vacantSeats;
    }
}

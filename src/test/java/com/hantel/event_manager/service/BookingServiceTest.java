package com.hantel.event_manager.service;

import com.hantel.event_manager.entity.Concert;
import com.hantel.event_manager.entity.hall.BookedSeat;
import com.hantel.event_manager.entity.hall.Hall;
import com.hantel.event_manager.entity.hall.Line;
import com.hantel.event_manager.repository.BookedSeatRepository;
import com.hantel.event_manager.repository.ConcertRepository;
import com.hantel.event_manager.repository.HallRepository;
import com.hantel.event_manager.repository.LineRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {
    @Mock
    private BookedSeatRepository bookedSeatRepository;
    @Mock
    private HallRepository hallRepository;
    @Mock
    private LineRepository lineRepository;
    @Spy
    private HallLayoutPrinter hallLayoutPrinter;

    @InjectMocks
    private BookingService bookingService;

    @Test
    public void getHallLayout() {
        long hallId = 1L;
        long concertId = 1L;

        Hall hall = new Hall();
        hall.setId(hallId);
        Concert concert = new Concert();

        List<Line> lines = createLines(hall, concertId);

        hall.setLines(lines);

        when(hallRepository.findById(hallId)).thenReturn(hall);

        String expectedHallOccupancy = """
                  Seat:  1  2  3  4  5  6  7  8  9 10 11 12 13 14
                Row  1: -- XX -- -- -- -- -- -- -- --
                Row  2: -- -- -- -- XX XX -- -- -- --
                Row  3: -- -- -- -- -- -- -- -- -- -- -- --
                Row  4: -- XX -- -- -- -- -- -- -- -- -- --
                Row  5: -- -- -- -- -- -- -- -- -- -- -- -- -- --""";

        assertEquals(expectedHallOccupancy, bookingService.getHallLayout(hallId, concertId));
    }

    private static List<Line> createLines(Hall hall, long concertId) {
        long anotherConcertId = 2L;

        List<BookedSeat> bookedSeatsLine1 = List.of(
                new BookedSeat(1L, hall,
                        new Concert(concertId, null, null, hall, null),
                        null, 2, null),
                new BookedSeat(2L, hall,
                        new Concert(anotherConcertId, null, null, hall, null),
                        null, 9, null));

        List<BookedSeat> bookedSeatsLine2 = List.of(
                new BookedSeat(3L, hall,
                        new Concert(concertId, null, null, hall, null),
                        null, 5, null),
                new BookedSeat(4L, hall,
                        new Concert(concertId, null, null, hall, null),
                        null, 6, null),
                new BookedSeat(5L, hall,
                        new Concert(anotherConcertId, null, null, hall, null),
                        null, 10, null));

        List<BookedSeat> bookedSeatsLine3 = List.of(
                new BookedSeat(6L, hall,
                        new Concert(anotherConcertId, null, null, hall, null),
                        null, 2, null));

        List<BookedSeat> bookedSeatsLine4 = List.of(
                new BookedSeat(7L, hall,
                        new Concert(anotherConcertId, null, null, hall, null),
                        null, 5, null),
                new BookedSeat(8L, hall,
                        new Concert(2L, null, null, hall, null),
                        null, 6, null),
                new BookedSeat(9L, hall,
                        new Concert(concertId, null, null, hall, null),
                        null, 2, null));

        List<BookedSeat> bookedSeatsLine5 = List.of(
                new BookedSeat(10L, hall,
                        new Concert(anotherConcertId, null, null, hall, null),
                        null, 5, null));

        return List.of(
                new Line(6L, 1, 10, hall, bookedSeatsLine1),
                new Line(7L, 2, 10, hall, bookedSeatsLine2),
                new Line(8L, 3, 12, hall, bookedSeatsLine3),
                new Line(9L, 4, 12, hall, bookedSeatsLine4),
                new Line(10L, 5, 14, hall, bookedSeatsLine5)
        );
    }

    @Test
    public void getVacantSeatsForLine() {
        Long lineId = 1L;
        int seatsPerLine = 14;

        Line line = new Line();
        line.setSeatsPerLine(seatsPerLine);

        List<BookedSeat> bookedSeats = List.of(
                new BookedSeat(1L, new Hall(), new Concert(), new Line(), 1, null),
                new BookedSeat(2L, new Hall(), new Concert(), new Line(), 2, null),
                new BookedSeat(3L, new Hall(), new Concert(), new Line(), 3, null),
                new BookedSeat(4L, new Hall(), new Concert(), new Line(), 4, null),
                new BookedSeat(5L, new Hall(), new Concert(), new Line(), 5, null),
                new BookedSeat(6L, new Hall(), new Concert(), new Line(), 6, null),
                new BookedSeat(7L, new Hall(), new Concert(), new Line(), 7, null),
                new BookedSeat(8L, new Hall(), new Concert(), new Line(), 8, null),
                new BookedSeat(9L, new Hall(), new Concert(), new Line(), 9, null),
                new BookedSeat(10L, new Hall(), new Concert(), new Line(), 10, null)
        );

        when(lineRepository.findById(lineId)).thenReturn(line);
        when(bookedSeatRepository.findAllByLineId(lineId)).thenReturn(bookedSeats);

        List<Integer> expectedResult = List.of(11, 12, 13, 14);

        assertEquals(expectedResult, bookingService.getVacantSeatsForLine(lineId));
    }
}
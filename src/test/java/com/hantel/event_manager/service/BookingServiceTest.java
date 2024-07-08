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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {
    @Mock
    private BookedSeatRepository bookedSeatRepository;
    @Mock
    private HallRepository hallRepository;
    @Mock
    private ConcertRepository concertRepository;
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

    @Test
    public void getHallLayout_WithCustomerBookedSeats() {
        long hallId = 1L;
        long concertId = 1L;

        Hall hall = new Hall();
        hall.setId(hallId);

        List<Line> lines = createLines(hall, concertId);

        hall.setLines(lines);

        when(hallRepository.findById(hallId)).thenReturn(hall);

        String expectedHallOccupancy = """
                  Seat:  1  2  3  4  5  6  7  8  9 10 11 12 13 14
                Row  1: -- XX -- -- -- -- -- -- -- --
                Row  2: -- Me -- -- XX XX -- -- Me --
                Row  3: -- -- -- -- -- -- -- -- -- -- -- --
                Row  4: -- XX -- -- -- -- -- -- -- -- -- --
                Row  5: -- -- -- -- -- -- -- -- -- -- -- -- -- --""";

        assertEquals(expectedHallOccupancy,
                bookingService.getHallLayout(hallId, concertId, createCustomerBookedSeats()));
    }

    private List<BookedSeat> createCustomerBookedSeats() {
        Line line = new Line();
        line.setId(7L);
        line.setOrdinalNumber(2);

        BookedSeat bookedSeat1 = new BookedSeat();
        bookedSeat1.setLine(line);
        bookedSeat1.setSeatOrdinalNumber(2);

        BookedSeat bookedSeat2 = new BookedSeat();
        bookedSeat2.setLine(line);
        bookedSeat2.setSeatOrdinalNumber(9);

        return List.of(bookedSeat1, bookedSeat2);
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

        long concertId = 1L;
        Concert concert = new Concert();
        concert.setId(concertId);

        Hall hall = new Hall();

        List<BookedSeat> bookedSeats = List.of(
                new BookedSeat(1L, hall, concert, line, 1, null),
                new BookedSeat(2L, hall, concert, line, 2, null),
                new BookedSeat(3L, hall, concert, line, 3, null),
                new BookedSeat(4L, hall, concert, line, 4, null),
                new BookedSeat(5L, hall, concert, line, 5, null),
                new BookedSeat(6L, hall, concert, line, 6, null),
                new BookedSeat(7L, hall, concert, line, 7, null),
                new BookedSeat(8L, hall, concert, line, 8, null),
                new BookedSeat(9L, hall, concert, line, 9, null),
                new BookedSeat(10L, hall, concert, line, 10, null)
        );

        when(lineRepository.findById(lineId)).thenReturn(line);
        when(bookedSeatRepository.findAllByLineIdAndConcertId(lineId, concertId)).thenReturn(bookedSeats);

        List<Integer> expectedResult = List.of(11, 12, 13, 14);

        assertEquals(expectedResult, bookingService.getVacantSeatsForLine(lineId, concertId));
    }

    @Test
    public void getVacantSeatsAmount_IfConcertIsFullyVacant() {
        Long concertId = 1L;
        Long hallId = 1L;

        int seatsAmountInHall = 10;
        int bookedSeatsAmount = 0;
        String expectedVacantSeatsAmount = "10";

        when(bookedSeatRepository.getBookedSeatsAmountByConcertId(hallId)).thenReturn(bookedSeatsAmount);
        when(hallRepository.getSeatsAmount(hallId)).thenReturn(seatsAmountInHall);
        String actualVacantSeatsAmount = bookingService.getVacantSeatsAmount(concertId, hallId);
        assertEquals(expectedVacantSeatsAmount, actualVacantSeatsAmount);
    }

    @Test
    public void getVacantSeatsAmount_IfConcertIsHalfVacant() {
        Long concertId = 1L;
        Long hallId = 1L;

        int seatsAmountInHall = 10;
        int bookedSeatsAmount = 5;
        String expectedVacantSeatsAmount = "5";

        when(bookedSeatRepository.getBookedSeatsAmountByConcertId(hallId)).thenReturn(bookedSeatsAmount);
        when(hallRepository.getSeatsAmount(hallId)).thenReturn(seatsAmountInHall);
        String actualVacantSeatsAmount = bookingService.getVacantSeatsAmount(concertId, hallId);
        assertEquals(expectedVacantSeatsAmount, actualVacantSeatsAmount);
    }

    @Test
    public void getVacantSeatsAmount_IfHallIsSoldOut() {
        Long concertId = 1L;
        Long hallId = 1L;

        int seatsAmountInHall = 10;
        int bookedSeatsAmount = 10;
        String expectedVacantSeatsAmount = "SOLD OUT";

        when(bookedSeatRepository.getBookedSeatsAmountByConcertId(hallId)).thenReturn(bookedSeatsAmount);
        when(hallRepository.getSeatsAmount(hallId)).thenReturn(seatsAmountInHall);
        String actualVacantSeatsAmount = bookingService.getVacantSeatsAmount(concertId, hallId);
        assertEquals(expectedVacantSeatsAmount, actualVacantSeatsAmount);
    }

    @Test
    public void getVacantLines_IfNoBookedSeats() {
        Long concertId = 1L;
        Concert concert = mock(Concert.class);
        Hall hall = mock(Hall.class);

        List<Line> lines = initializeLines();

        List<BookedSeat> bookedSeats = new ArrayList<>();

        when(concertRepository.findById(concertId)).thenReturn(concert);
        when(concert.getHall()).thenReturn(hall);
        when(hall.getLines()).thenReturn(lines);
        when(concert.getBookedSeats()).thenReturn(bookedSeats);

        List<LineTicketControllerDTO> expectedResult = List.of(
                new LineTicketControllerDTO(1L, 1),
                new LineTicketControllerDTO(2L, 2)
        );

        assertEquals(expectedResult.toString(), bookingService.getVacantLines(concertId).toString());
    }

    @Test
    public void getVacantLines_IfOneVacantLine() {
        Long concertId = 1L;
        Concert concert = mock(Concert.class);
        Hall hall = mock(Hall.class);

        List<Line> lines = initializeLines();

        List<BookedSeat> bookedSeats = initializeBookedSeatsForOneBookedLine();

        when(concertRepository.findById(concertId)).thenReturn(concert);
        when(concert.getHall()).thenReturn(hall);
        when(hall.getLines()).thenReturn(lines);
        when(concert.getBookedSeats()).thenReturn(bookedSeats);

        List<LineTicketControllerDTO> expectedResult = List.of(
                new LineTicketControllerDTO(2L, 2)
        );

        assertEquals(expectedResult.toString(), bookingService.getVacantLines(concertId).toString());
    }

    @Test
    public void getVacantLines_IfSoldOut() {
        Long concertId = 1L;
        Concert concert = mock(Concert.class);
        Hall hall = mock(Hall.class);

        List<Line> lines = initializeLines();

        List<BookedSeat> bookedSeats = initializeBookedSeatsForSoldOutLine();

        when(concertRepository.findById(concertId)).thenReturn(concert);
        when(concert.getHall()).thenReturn(hall);
        when(hall.getLines()).thenReturn(lines);
        when(concert.getBookedSeats()).thenReturn(bookedSeats);

        assertEquals(List.of().toString(), bookingService.getVacantLines(concertId).toString());
    }

    private List<Line> initializeLines() {
        Line line1 = new Line();
        line1.setId(1L);
        line1.setOrdinalNumber(1);
        line1.setSeatsPerLine(2);
        Line line2 = new Line();
        line2.setId(2L);
        line2.setOrdinalNumber(2);
        line2.setSeatsPerLine(2);
        return List.of(line1, line2);
    }

    private List<BookedSeat> initializeBookedSeatsForOneBookedLine() {
        Line line = new Line();
        line.setOrdinalNumber(1);

        BookedSeat bookedSeat1 = new BookedSeat();
        bookedSeat1.setLine(line);

        BookedSeat bookedSeat2 = new BookedSeat();
        bookedSeat2.setLine(line);

        return List.of(bookedSeat1, bookedSeat2);
    }

    private List<BookedSeat> initializeBookedSeatsForSoldOutLine() {
        Line line1 = new Line();
        line1.setOrdinalNumber(1);

        Line line2 = new Line();
        line2.setOrdinalNumber(2);

        BookedSeat bookedSeat1Line1 = new BookedSeat();
        bookedSeat1Line1.setLine(line1);

        BookedSeat bookedSeat2Line1 = new BookedSeat();
        bookedSeat2Line1.setLine(line1);

        BookedSeat bookedSeat1Line2 = new BookedSeat();
        bookedSeat1Line2.setLine(line2);

        BookedSeat bookedSeat2Line2 = new BookedSeat();
        bookedSeat2Line2.setLine(line2);

        return List.of(bookedSeat1Line1, bookedSeat2Line1, bookedSeat1Line2, bookedSeat2Line2);
    }
}
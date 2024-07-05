package com.hantel.event_manager.controller;

import com.hantel.event_manager.dto.ConcertTicketControllerDTO;
import com.hantel.event_manager.entity.Concert;
import com.hantel.event_manager.entity.Customer;
import com.hantel.event_manager.entity.hall.BookedSeat;
import com.hantel.event_manager.entity.hall.Line;
import com.hantel.event_manager.service.BookingService;
import com.hantel.event_manager.service.ConcertService;
import com.hantel.event_manager.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("ticket")
public class TicketController {
    public static final Logger LOGGER = LoggerFactory.getLogger(TicketController.class);
    private final ConcertService concertService;
    private final BookingService bookingService;
    private final CustomerService customerService;

    public TicketController(ConcertService concertService,
                            BookingService bookingService,
                            CustomerService customerService) {
        this.concertService = concertService;
        this.bookingService = bookingService;
        this.customerService = customerService;
    }

    @GetMapping
    public String showTicketMenu() {
        return "/ticket/menu";
    }

    @GetMapping("/overviewConcerts")
    public String showAllConcerts(Model model) {
        List<Concert> concerts = concertService.findAll();
        List<ConcertTicketControllerDTO> concertTicketControllerDTOList = concerts
                .stream().map(concert -> new ConcertTicketControllerDTO(
                        concert.getId(),
                        concert.getMusical().getName(),
                        concert.getStartDateTime().toLocalDate().toString(),
                        concert.getStartDateTime().toLocalTime().toString(),
                        bookingService.getVacantSeatsAmount(concert.getId(), concert.getHall().getId()))).toList();

        model.addAttribute("concerts", concertTicketControllerDTOList);

        return "/ticket/overview-concerts";
    }

    @GetMapping("/bookingForm")
    public String showBookingForm(@RequestParam("concertId") Long concertId,
                                  Model model) {
        Concert concert = concertService.findById(concertId);
        model.addAttribute("concertId", concertId);
        model.addAttribute("musicalName", concert.getMusical().getName());
        model.addAttribute("date", concert.getStartDateTime().toLocalDate());
        model.addAttribute("time", concert.getStartDateTime().toLocalTime());
        model.addAttribute("hallName", concert.getHall().getName());
        model.addAttribute("price", concert.getMusical().getPrice());
        model.addAttribute("hallLayout", bookingService.getHallLayout(
                concert.getHall().getId(),
                concert.getId()));
        model.addAttribute("vacantRows", bookingService.getVacantLines(concertId));

        return "ticket/booking-form";
    }

    @GetMapping("/getVacantSeats")
    @ResponseBody
    public List<Integer> getVacantSeats(@RequestParam("rowId") Long rowId) {
        return bookingService.getVacantSeatsForLine(rowId);
    }

    @PostMapping("/buyTicket")
    @Transactional
    public String buyTicket(@RequestParam("customerName") String customerName,
                            @RequestParam("customerPhone") String customerPhone,
                            @RequestParam("rowSelect") Long lineId,
                            @RequestParam("seatSelect") int seatOrdinalNumber,
                            @RequestParam("concertId") Long concertId,
                            Model model) {
        Concert concert = concertService.findById(concertId);
        Line line = new Line();
        line.setId(lineId);

        Customer customer = new Customer();
        customer.setName(customerName);
        customer.setPhoneNumber(customerPhone);

        customerService.save(customer);

        BookedSeat bookedSeat = new BookedSeat();
        bookedSeat.setConcert(concert);
        bookedSeat.setHall(concert.getHall());
        bookedSeat.setLine(line);
        bookedSeat.setSeatOrdinalNumber(seatOrdinalNumber);
        bookedSeat.setCustomer(customer);

        bookedSeat = bookingService.save(bookedSeat);

        model.addAttribute("customerName", customerName);
        model.addAttribute("customerPhone", customerPhone);
        model.addAttribute("date", bookedSeat.getConcert().getStartDateTime().toLocalDate());
        model.addAttribute("time", bookedSeat.getConcert().getStartDateTime().toLocalTime());
        model.addAttribute("hall", bookedSeat.getHall().getName());
        model.addAttribute("price", bookedSeat.getConcert().getMusical().getPrice());
        model.addAttribute("row", bookedSeat.getLine().getOrdinalNumber());
        model.addAttribute("seat", bookedSeat.getSeatOrdinalNumber());
        model.addAttribute("hallLayout", bookingService.getHallLayout(
                bookedSeat.getHall().getId(),
                bookedSeat.getConcert().getId(),
                List.of(bookedSeat)));

        return "ticket/buy-ticket-success";
    }
}

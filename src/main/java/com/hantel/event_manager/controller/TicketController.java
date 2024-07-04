package com.hantel.event_manager.controller;

import com.hantel.event_manager.dto.ConcertTicketControllerDTO;
import com.hantel.event_manager.entity.Concert;
import com.hantel.event_manager.service.BookingService;
import com.hantel.event_manager.service.ConcertService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("ticket")
public class TicketController {
    public static final Logger LOGGER = LoggerFactory.getLogger(TicketController.class);
    private final ConcertService concertService;
    private final BookingService bookingService;

    public TicketController(ConcertService concertService, BookingService bookingService) {
        this.concertService = concertService;
        this.bookingService = bookingService;
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
        model.addAttribute("musicalName", concert.getMusical().getName());
        model.addAttribute("date", concert.getStartDateTime().toLocalDate());
        model.addAttribute("time", concert.getStartDateTime().toLocalTime());
        model.addAttribute("hallName", concert.getHall().getName());
        model.addAttribute("price", concert.getMusical().getPrice());
        model.addAttribute("hallOccupancy", bookingService.getHallOccupancy(
                concert.getHall().getId(),
                concert.getId()));
        model.addAttribute("vacantRows", List.of(1, 2, 3, 4, 5));

        return "ticket/booking-form";
    }

    @GetMapping("/getVacantSeats")
    @ResponseBody
    public List<Integer> getFreeSeats(@RequestParam("row") int row) {
        return List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
    }
}

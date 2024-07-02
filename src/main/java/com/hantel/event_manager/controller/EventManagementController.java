package com.hantel.event_manager.controller;

import com.hantel.event_manager.entity.Concert;
import com.hantel.event_manager.entity.Musical;
import com.hantel.event_manager.entity.hall.Hall;
import com.hantel.event_manager.service.ConcertService;
import com.hantel.event_manager.service.HallService;
import com.hantel.event_manager.service.MusicalService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Controller
@RequestMapping("manager")
public class EventManagementController {
    public static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(EventManagementController.class);

    private final MusicalService musicalService;
    private final ConcertService concertService;
    private final HallService hallService;

    @Autowired
    public EventManagementController(MusicalService musicalService,
                                     ConcertService concertService,
                                     HallService hallService) {
        this.musicalService = musicalService;
        this.concertService = concertService;
        this.hallService = hallService;
    }

    @GetMapping("/")
    public String showMusicalForm(Model model) {
        return "manager/new-musical-form";
    }

    @PostMapping("/createMusical")
    public String createMusical(@RequestParam("name") String name,
                                @RequestParam("price") Double price,
                                RedirectAttributes redirectAttributes,
                                HttpSession session) {
        Musical musical = new Musical();
        musical.setName(name);
        musical.setPrice(price);
        musicalService.save(musical);

        List<Hall> halls = hallService.findAll();
        List<String> reservedDates = concertService.getReservedDatesAsStrings();

        redirectAttributes.addAttribute("musicalId", musical.getId());
        redirectAttributes.addAttribute("name", name);
        redirectAttributes.addAttribute("price", price);

        session.setAttribute("halls", halls);
        session.setAttribute("reservedDates", reservedDates);

        return "redirect:/manager/showConcertForm";
    }

    @GetMapping("/showConcertForm")
    public String showConcertForm(@RequestParam("musicalId") Long musicalId,
                                  @RequestParam("name") String name,
                                  @RequestParam("price") Double price,
                                  HttpSession session,
                                  Model model) {
        model.addAttribute("musicalId", musicalId);
        model.addAttribute("name", name);
        model.addAttribute("price", price);
        model.addAttribute("halls", session.getAttribute("halls"));
        model.addAttribute("reservedDates", session.getAttribute("reservedDates"));
        return "manager/new-concert-form";
    }

    @PostMapping("/createConcert")
    public String createConcert(@RequestParam("musicalId") Long musicalId,
                                @RequestParam("hall") Long hallId,
                                @RequestParam("date") String date,
                                @RequestParam("time") String time,
                                Model model) {
        Musical musical = musicalService.findById(musicalId);
        Hall hall = hallService.findById(hallId);

        Concert concert = new Concert();
        concert.setMusical(musical);
        concert.setHall(hall);
        concert.setStartDateTime(parseDateTime(date, time));

        concertService.save(concert);

        model.addAttribute("name", concert.getMusical().getName());
        model.addAttribute("date", concert.getStartDateTime().toLocalDate().toString());
        model.addAttribute("time", concert.getStartDateTime().toLocalTime().toString());
        model.addAttribute("hall", hall.getName());
        model.addAttribute("price", concert.getMusical().getPrice());

        return "manager/create-concert-success";
    }

    private LocalDateTime parseDateTime(String date, String time) {
        LocalDateTime localDateTime;

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        try {
            LocalDate localDate = LocalDate.parse(date, dateFormatter);
            LocalTime localTime = LocalTime.parse(time, timeFormatter);
            localDateTime = LocalDateTime.of(localDate, localTime);
        } catch (DateTimeParseException e) {
            System.err.println("LocalDateTime parse exception: " + e.getMessage());
            throw new RuntimeException("LocalDateTime parse exception: " + e.getMessage());
        }

        return localDateTime;
    }
}

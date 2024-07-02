package com.hantel.event_manager.controller;

import com.hantel.event_manager.entity.Concert;
import com.hantel.event_manager.entity.Musical;
import com.hantel.event_manager.service.ConcertService;
import com.hantel.event_manager.service.MusicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("manager")
public class EventManagementController {
    private final MusicalService musicalService;
    private final ConcertService concertService;

    @Autowired
    public EventManagementController(MusicalService musicalService, ConcertService concertService) {
        this.musicalService = musicalService;
        this.concertService = concertService;
    }

    @GetMapping("/")
    public String showForm(Model model) {
        return "manager/create-musical";
    }

    @PostMapping("/createMusical")
    public String createMusical(@RequestParam("name") String name,
                                @RequestParam("price") Double price,
                                Model model) {
        Musical musical = new Musical();
        musical.setName(name);
        musical.setPrice(price);
        musicalService.save(musical);

        model.addAttribute("name", name);
        model.addAttribute("price", price);
        model.addAttribute("reservedDates", concertService.getReservedDatesAsStrings());

        return "manager/create-concert";

    }

    public void createConcert(Concert concert) {

    }

    public List<LocalDateTime> getFreeDates() {
        return new ArrayList<>();
    }
}

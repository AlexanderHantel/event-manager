package com.hantel.event_manager.controller;

import com.hantel.event_manager.entity.Concert;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TicketController {

    public List<Concert> getConcerts() {
        return new ArrayList<>();
    }
}

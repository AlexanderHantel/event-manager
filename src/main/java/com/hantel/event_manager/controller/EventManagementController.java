package com.hantel.event_manager.controller;

import com.hantel.event_manager.entity.Concert;
import com.hantel.event_manager.entity.Musical;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class EventManagementController {

    public void createMusical(Musical musical) {

    }

    public void createConcert(Concert concert) {

    }

    public List<LocalDateTime> getFreeDates() {
        return new ArrayList<>();
    }
}

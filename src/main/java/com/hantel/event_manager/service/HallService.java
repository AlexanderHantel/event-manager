package com.hantel.event_manager.service;

import com.hantel.event_manager.entity.hall.Hall;
import com.hantel.event_manager.entity.hall.Line;
import com.hantel.event_manager.entity.hall.Seat;
import com.hantel.event_manager.repository.HallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HallService {
    private final HallRepository hallRepository;

    @Autowired
    public HallService(HallRepository hallRepository) {
        this.hallRepository = hallRepository;
    }

    public String createHallString() {
        StringBuilder hallString = new StringBuilder();
        Hall hall = hallRepository.findById(1L);
        List<Line> lines = hall.getLines();

        for (Line line : lines) {
            List<Seat> seats = line.getSeats();
            hallString.append("\n");
            for(Seat seat : seats) {
                if (seat.getIsFree()) {
                    hallString.append(" == ");
                } else {
                    hallString.append(" XX ");
                }
            }
        }

        return hallString.toString();
    }

    public List<Hall> findAll() {
        return hallRepository.findAll();
    }

    public Hall findById(Long id) {
        return hallRepository.findById(id);
    }
}

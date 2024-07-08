package com.hantel.event_manager.service;

import com.hantel.event_manager.entity.hall.Hall;
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

    public List<Hall> findAll() {
        return hallRepository.findAll();
    }

    public Hall findById(Long id) {
        return hallRepository.findById(id);
    }
}

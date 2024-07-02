package com.hantel.event_manager.service;

import com.hantel.event_manager.entity.Musical;
import com.hantel.event_manager.repository.MusicalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MusicalService {
    private final MusicalRepository musicalRepository;

    @Autowired
    public MusicalService(MusicalRepository musicalRepository) {
        this.musicalRepository = musicalRepository;
    }

    public Musical findById(int id) {
        return musicalRepository.findById(id);
    }

    public List<Musical> findAll() {
        return musicalRepository.findAll();
    }

    public void save(Musical musical) {
        musicalRepository.save(musical);
    }
}

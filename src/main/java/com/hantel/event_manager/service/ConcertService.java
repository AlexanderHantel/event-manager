package com.hantel.event_manager.service;

import com.hantel.event_manager.entity.Concert;
import com.hantel.event_manager.repository.ConcertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ConcertService {
    private final ConcertRepository concertRepository;

    @Autowired
    public ConcertService(ConcertRepository concertRepository) {
        this.concertRepository = concertRepository;
    }

    public List<Concert> findAll() {
        return concertRepository.findAll();
    }

    public List<String> getReservedDatesAsStrings() {
        return findAll().stream()
                .map(Concert::getStartDateTime)
                .map(LocalDateTime::toLocalDate)
                .map(LocalDate::toString)
                .collect(Collectors.toList());
    }

    public void save(Concert concert){
        concertRepository.save(concert);
    }

    public List<Concert> findAllByMusicalId(Long musicalId) {
        return concertRepository.findAllByMusicalId(musicalId);
    }

    public Concert findById(Long id) {
        return concertRepository.findById(id);
    }
}

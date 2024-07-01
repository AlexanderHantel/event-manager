package com.hantel.event_manager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Musical {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Double price;

    @OneToMany(mappedBy = "musical")
    private List<Concert> concerts = new ArrayList<>();

    public void addConcert(Concert concert) {
        concerts.add(concert);
    }
}

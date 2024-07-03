package com.hantel.event_manager.entity.hall;

import com.hantel.event_manager.entity.Concert;
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
public class Hall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "hall")
    private List<Line> lines = new ArrayList<>();

    @OneToMany(mappedBy = "hall")
    private List<BookedSeat> hallOccupancies = new ArrayList<>();

    @OneToMany(mappedBy = "hall")
    private List<Concert> concerts = new ArrayList<>();
}

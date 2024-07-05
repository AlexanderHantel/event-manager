package com.hantel.event_manager.entity.hall;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Line {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int ordinalNumber;

    private int seatsPerLine;

    @ManyToOne
    private Hall hall;

    @OneToMany(mappedBy = "line")
    private List<BookedSeat> bookedSeats;
}

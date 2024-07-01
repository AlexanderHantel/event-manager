package com.hantel.event_manager.entity;

import com.hantel.event_manager.entity.hall.Seat;
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
public class Ticket {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Concert concert;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "ticket")
    private Viewer viewer;

    @ManyToMany
    @JoinTable(
            name = "TICKET_SEAT",
            joinColumns = @JoinColumn(name = "TICKET_ID"),
            inverseJoinColumns = @JoinColumn(name = "SEAT_ID"))
    private List<Seat> seats = new ArrayList<>();


}

package com.hantel.event_manager.entity.hall;

import com.hantel.event_manager.entity.Ticket;
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
public class Seat {

    @Id
    private Long id;
    private Long number;

    @ManyToOne
    private Line line;

    private Boolean isFree;

    @ManyToMany(mappedBy = "seats")
    private List<Ticket> tickets = new ArrayList<>();
}

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
    @Column(name = "number")
    private Long number;

    @OneToMany(mappedBy = "line")
    private List<Seat> seats;

    @ManyToOne
    private Hall hall;
}

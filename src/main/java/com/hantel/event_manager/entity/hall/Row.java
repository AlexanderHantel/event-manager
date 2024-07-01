package com.hantel.event_manager.entity.hall;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
public class Row {

    @Id
    private Long number;

    @OneToMany(mappedBy = "row")
    private List<Seat> seats;

    @ManyToOne
    private Hall hall;
}

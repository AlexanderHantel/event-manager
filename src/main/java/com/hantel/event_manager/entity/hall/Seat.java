package com.hantel.event_manager.entity.hall;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Seat {

    @Id
    private Long number;

    @ManyToOne
    private Row row;

    private Boolean isBooked;
}

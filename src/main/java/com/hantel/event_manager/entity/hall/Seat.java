package com.hantel.event_manager.entity.hall;

import jakarta.persistence.*;
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
    private Long id;
    private Long number;

    @ManyToOne
    private Line line;

    private Boolean isFree;
}

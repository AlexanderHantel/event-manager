package com.hantel.event_manager.entity.hall;

import com.hantel.event_manager.entity.Concert;
import com.hantel.event_manager.entity.Customer;
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
public class BookedSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Hall hall;

    @ManyToOne
    private Concert concert;

    @ManyToOne
    private Line line;
    private int seatOrdinalNumber;

    @ManyToOne
    private Customer customer;
}

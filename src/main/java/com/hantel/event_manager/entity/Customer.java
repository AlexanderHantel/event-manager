package com.hantel.event_manager.entity;

import com.hantel.event_manager.entity.hall.BookedSeat;
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
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String phoneNumber;

    @OneToMany(mappedBy = "customer")
    private List<BookedSeat> bookedSeats;
}

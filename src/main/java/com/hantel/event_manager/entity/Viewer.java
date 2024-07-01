package com.hantel.event_manager.entity;

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
public class Viewer {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String phoneNumber;

    @OneToOne(fetch = FetchType.LAZY)
    private Ticket ticket;
}

package com.eventmanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String typeName;      // Regular, VIP
    private double price;
    private int totalQuantity;
    private int soldQuantity;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
}

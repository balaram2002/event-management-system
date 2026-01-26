package com.eventmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EventStatsDTO {

    private Long eventId;
    private String eventName;
    private int ticketsSold;
    private double revenue;
}

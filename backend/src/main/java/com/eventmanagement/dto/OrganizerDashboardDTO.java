package com.eventmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class OrganizerDashboardDTO {

    private int totalEvents;
    private int totalTicketsSold;
    private double totalRevenue;
    private List<EventStatsDTO> events;
}

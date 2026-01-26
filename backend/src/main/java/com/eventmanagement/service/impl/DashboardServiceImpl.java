package com.eventmanagement.service.impl;


import com.eventmanagement.dto.EventStatsDTO;
import com.eventmanagement.dto.OrganizerDashboardDTO;
import com.eventmanagement.entity.User;
import com.eventmanagement.exception.ResourceNotFoundException;
import com.eventmanagement.repository.EventRepository;
import com.eventmanagement.repository.RegistrationRepository;
import com.eventmanagement.repository.UserRepository;
import com.eventmanagement.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final RegistrationRepository registrationRepository;

    @Override
    public OrganizerDashboardDTO getOrganizerDashboard(String organizerEmail) {

        User organizer = userRepository.findByEmail(organizerEmail)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Organizer not found"));

        int totalEvents = eventRepository
                .findByOrganizerId(organizer.getId())
                .size();

        Integer ticketsSold =
                registrationRepository.getTotalTicketsSold(organizer.getId());

        Double revenue =
                registrationRepository.getTotalRevenue(organizer.getId());

        List<EventStatsDTO> eventStats =
                registrationRepository.getEventWiseStats(organizer.getId())
                        .stream()
                        .map(row -> new EventStatsDTO(
                                (Long) row[0],
                                (String) row[1],
                                ((Long) row[2]).intValue(),
                                (Double) row[3]
                        ))
                        .toList();

        return new OrganizerDashboardDTO(
                totalEvents,
                ticketsSold != null ? ticketsSold : 0,
                revenue != null ? revenue : 0.0,
                eventStats
        );
    }
}

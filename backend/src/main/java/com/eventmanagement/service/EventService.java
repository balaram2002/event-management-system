package com.eventmanagement.service;


import com.eventmanagement.dto.EventRequestDTO;
import com.eventmanagement.dto.EventResponseDTO;
import com.eventmanagement.entity.Event;

import java.time.LocalDate;
import java.util.List;

public interface EventService {

    EventResponseDTO createEvent(EventRequestDTO request, String organizerEmail);

    List<EventResponseDTO> getAllEvents();

    List<EventResponseDTO> getEventsByOrganizer(String organizerEmail);

    List<EventResponseDTO> searchEvents(
            String keyword,
            String location,
            LocalDate eventDate
    );

}

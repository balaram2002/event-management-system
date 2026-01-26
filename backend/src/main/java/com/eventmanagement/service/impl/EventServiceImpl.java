package com.eventmanagement.service.impl;

import com.eventmanagement.dto.EventRequestDTO;
import com.eventmanagement.dto.EventResponseDTO;
import com.eventmanagement.dto.UserDTO;
import com.eventmanagement.entity.Event;
import com.eventmanagement.entity.User;
import com.eventmanagement.exception.ResourceNotFoundException;
import com.eventmanagement.repository.EventRepository;
import com.eventmanagement.repository.UserRepository;
import com.eventmanagement.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    @Override
    public EventResponseDTO createEvent(EventRequestDTO request, String organizerEmail) {

        User organizer = userRepository.findByEmail(organizerEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Organizer not found"));

        Event event = new Event();
        event.setTitle(request.getTitle());
        event.setDescription(request.getDescription());
        event.setEventDate(request.getEventDate());
        event.setEventTime(request.getEventTime());
        event.setLocation(request.getLocation());
        event.setOrganizer(organizer);

        return mapToDTO(eventRepository.save(event));
    }

    @Override
    public List<EventResponseDTO> getAllEvents() {
        return eventRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public List<EventResponseDTO> getEventsByOrganizer(String organizerEmail) {

        User organizer = userRepository.findByEmail(organizerEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Organizer not found"));

        return eventRepository.findByOrganizerId(organizer.getId())
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public List<EventResponseDTO> searchEvents(
            String keyword,
            String location,
            LocalDate eventDate
    ) {
        return eventRepository.searchEvents(keyword, location, eventDate)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    //  SINGLE MAPPER (IMPORTANT)
    private EventResponseDTO mapToDTO(Event event) {

        UserDTO organizerDTO = new UserDTO(
                event.getOrganizer().getId(),
                event.getOrganizer().getName(),
                event.getOrganizer().getEmail()
        );

        return new EventResponseDTO(
                event.getId(),
                event.getTitle(),
                event.getLocation(),
                event.getEventDate(),
                event.getEventTime(),
                organizerDTO
        );
    }
}

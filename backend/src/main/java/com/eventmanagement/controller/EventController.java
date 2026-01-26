package com.eventmanagement.controller;




import com.eventmanagement.dto.EventRequestDTO;
import com.eventmanagement.dto.EventResponseDTO;
import com.eventmanagement.entity.Event;
import com.eventmanagement.service.EventService;
import com.eventmanagement.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @PostMapping
    public ResponseEntity<ApiResponse<?>> createEvent(
            @RequestBody EventRequestDTO request,
            Authentication authentication
    ) {

        String organizerEmail = authentication.getName();
        EventResponseDTO event = eventService.createEvent(request, organizerEmail);

        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .message("Event created successfully")
                        .data(event)
                        .status(201)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getAllEvents() {

        List<EventResponseDTO> events = eventService.getAllEvents();

        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .message("Events fetched successfully")
                        .data(events)
                        .status(200)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<?>> searchEvents(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate eventDate
    ) {

        List<EventResponseDTO> events =
                eventService.searchEvents(keyword, location, eventDate);

        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .message("Events fetched successfully")
                        .data(events)
                        .status(200)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }


    @GetMapping("/my")
    public ResponseEntity<ApiResponse<?>> getMyEvents(Authentication authentication) {

        String email = authentication.getName();
        List<EventResponseDTO> events = eventService.getEventsByOrganizer(email);

        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .message("Organizer events fetched successfully")
                        .data(events)
                        .status(200)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
}

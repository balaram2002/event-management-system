package com.eventmanagement.controller;

import com.eventmanagement.dto.TicketRequestDTO;
import com.eventmanagement.dto.TicketResponseDTO;
import com.eventmanagement.service.TicketService;
import com.eventmanagement.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/events/{eventId}/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping
    public ResponseEntity<ApiResponse<?>> createTicket(
            @PathVariable Long eventId,
            @RequestBody TicketRequestDTO request,
            Authentication authentication
    ) {

        String organizerEmail = authentication.getName();
        TicketResponseDTO ticket = ticketService.createTicket(eventId, request, organizerEmail);

        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .message("Ticket created successfully")
                        .data(ticket)
                        .status(201)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getTickets(@PathVariable Long eventId) {

        List<TicketResponseDTO> tickets = ticketService.getTicketsByEvent(eventId);

        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .message("Tickets fetched successfully")
                        .data(tickets)
                        .status(200)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }


}
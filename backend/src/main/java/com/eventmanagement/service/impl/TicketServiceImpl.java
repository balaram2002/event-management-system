package com.eventmanagement.service.impl;

import com.eventmanagement.dto.TicketRequestDTO;
import com.eventmanagement.dto.TicketResponseDTO;
import com.eventmanagement.entity.Event;
import com.eventmanagement.entity.TicketType;
import com.eventmanagement.exception.InvalidOperationException;
import com.eventmanagement.exception.ResourceNotFoundException;
import com.eventmanagement.repository.EventRepository;
import com.eventmanagement.repository.TicketTypeRepository;
import com.eventmanagement.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final EventRepository eventRepository;
    private final TicketTypeRepository ticketTypeRepository;

    @Override
    public TicketResponseDTO createTicket(Long eventId, TicketRequestDTO request, String organizerEmail) {

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found"));

        if (!event.getOrganizer().getEmail().equals(organizerEmail)) {
            throw new InvalidOperationException("You are not allowed to add tickets");
        }

        TicketType ticket = new TicketType();
        ticket.setTypeName(request.getTypeName());
        ticket.setPrice(request.getPrice());
        ticket.setTotalQuantity(request.getTotalQuantity());
        ticket.setSoldQuantity(0);
        ticket.setEvent(event);

        TicketType savedTicket = ticketTypeRepository.save(ticket);

        return mapToDTO(savedTicket);
    }

    @Override
    public List<TicketResponseDTO> getTicketsByEvent(Long eventId) {

        return ticketTypeRepository.findByEventId(eventId)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    /* ---------- MAPPER ---------- */
    private TicketResponseDTO mapToDTO(TicketType ticket) {
        return new TicketResponseDTO(
                ticket.getId(),
                ticket.getTypeName(),
                ticket.getPrice(),
                ticket.getTotalQuantity(),
                ticket.getSoldQuantity(),
                ticket.getTotalQuantity() - ticket.getSoldQuantity()
        );
    }

}
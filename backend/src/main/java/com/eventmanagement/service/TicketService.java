package com.eventmanagement.service;


import com.eventmanagement.dto.TicketRequestDTO;
import com.eventmanagement.dto.TicketResponseDTO;

import java.util.List;

public interface TicketService {

    TicketResponseDTO createTicket(Long eventId, TicketRequestDTO request, String organizerEmail);

    List<TicketResponseDTO> getTicketsByEvent(Long eventId);
}

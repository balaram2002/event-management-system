package com.eventmanagement.service.impl;

import com.eventmanagement.dto.RegistrationRequestDTO;
import com.eventmanagement.dto.RegistrationResponseDTO;
import com.eventmanagement.service.RegistrationService;
import com.eventmanagement.entity.Registration;
import com.eventmanagement.entity.TicketType;
import com.eventmanagement.entity.User;
import com.eventmanagement.exception.InvalidOperationException;
import com.eventmanagement.exception.ResourceNotFoundException;
import com.eventmanagement.repository.RegistrationRepository;
import com.eventmanagement.repository.TicketTypeRepository;
import com.eventmanagement.repository.UserRepository;
import com.eventmanagement.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final TicketTypeRepository ticketTypeRepository;
    private final RegistrationRepository registrationRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public RegistrationResponseDTO bookTicket(
            RegistrationRequestDTO request,
            String userEmail
    ) {

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        TicketType ticket = ticketTypeRepository.findById(request.getTicketTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found"));

        int available = ticket.getTotalQuantity() - ticket.getSoldQuantity();

        if (request.getQuantity() > available) {
            throw new InvalidOperationException("Not enough tickets available");
        }

        // Update sold quantity
        ticket.setSoldQuantity(ticket.getSoldQuantity() + request.getQuantity());

        double totalAmount = ticket.getPrice() * request.getQuantity();

        Registration registration = new Registration();
        registration.setUser(user);
        registration.setEvent(ticket.getEvent());
        registration.setTicketType(ticket);
        registration.setQuantity(request.getQuantity());
        registration.setTotalAmount(totalAmount);
        registration.setPaymentStatus("PAID"); // simulated payment

        Registration saved = registrationRepository.save(registration);

        return new RegistrationResponseDTO(
                saved.getId(),
                saved.getEvent().getTitle(),   // maps to eventName
                ticket.getTypeName(),
                saved.getQuantity(),
                saved.getTotalAmount(),
                saved.getPaymentStatus()
        );

    }
}

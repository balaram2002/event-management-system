package com.eventmanagement.service;

import com.eventmanagement.dto.RegistrationRequestDTO;
import com.eventmanagement.dto.RegistrationResponseDTO;

public interface RegistrationService {

    RegistrationResponseDTO bookTicket(
            RegistrationRequestDTO request,
            String userEmail
    );
}

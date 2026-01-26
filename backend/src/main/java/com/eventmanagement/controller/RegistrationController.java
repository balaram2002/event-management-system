package com.eventmanagement.controller;

import com.eventmanagement.dto.RegistrationRequestDTO;
import com.eventmanagement.dto.RegistrationResponseDTO;
import com.eventmanagement.service.RegistrationService;
import com.eventmanagement.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/registrations")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<ApiResponse<?>> bookTicket(
            @RequestBody RegistrationRequestDTO request,
            Authentication authentication
    ) {

        String userEmail = authentication.getName();
        RegistrationResponseDTO responseDTO =
                registrationService.bookTicket(request, userEmail);

        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .message("Ticket booked successfully")
                        .data(responseDTO)
                        .status(200)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
}

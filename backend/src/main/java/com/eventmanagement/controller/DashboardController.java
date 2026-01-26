package com.eventmanagement.controller;



import com.eventmanagement.service.DashboardService;
import com.eventmanagement.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/organizer")
    public ResponseEntity<ApiResponse<?>> organizerDashboard(
            Authentication authentication
    ) {

        String organizerEmail = authentication.getName();

        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .message("Organizer dashboard fetched successfully")
                        .data(
                                dashboardService
                                        .getOrganizerDashboard(organizerEmail)
                        )
                        .status(200)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
}

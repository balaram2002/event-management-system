package com.eventmanagement.controller;


import com.eventmanagement.dto.FeedbackRequestDTO;
import com.eventmanagement.service.FeedbackService;
import com.eventmanagement.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/feedback")
@RequiredArgsConstructor
public class FeedbackController {

    private final FeedbackService feedbackService;

    @PostMapping
    public ResponseEntity<ApiResponse<?>> addFeedback(
            @RequestBody FeedbackRequestDTO request,
            Authentication authentication
    ) {

        String userEmail = authentication.getName();

        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .message("Feedback submitted successfully")
                        .data(
                                feedbackService.addFeedback(request, userEmail)
                        )
                        .status(200)
                        .timestamp(LocalDateTime.now())
                        .build()
        );
    }
}

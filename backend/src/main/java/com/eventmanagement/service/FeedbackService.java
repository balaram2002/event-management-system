package com.eventmanagement.service;


import com.eventmanagement.dto.FeedbackRequestDTO;
import com.eventmanagement.dto.FeedbackResponseDTO;

public interface FeedbackService {
    FeedbackResponseDTO addFeedback(
            FeedbackRequestDTO request,
            String userEmail
    );
}

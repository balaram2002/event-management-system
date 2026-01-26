package com.eventmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FeedbackResponseDTO {
    private Long feedbackId;
    private String eventName;
    private String userEmail;
    private String comment;
}

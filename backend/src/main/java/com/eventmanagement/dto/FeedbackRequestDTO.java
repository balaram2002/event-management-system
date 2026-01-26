package com.eventmanagement.dto;
import lombok.Data;

@Data
public class FeedbackRequestDTO {
    private Long eventId;
    private String comment;
}

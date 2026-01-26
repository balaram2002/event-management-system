package com.eventmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationResponseDTO {
    private Long registrationId;
    private String eventName;
    private String ticketType;
    private int quantity;
    private double totalAmount;
    private String paymentStatus;
}

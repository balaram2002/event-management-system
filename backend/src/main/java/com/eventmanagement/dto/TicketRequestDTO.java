package com.eventmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
   public class TicketRequestDTO {
    private String typeName;
    private double price;
    private int totalQuantity;
}

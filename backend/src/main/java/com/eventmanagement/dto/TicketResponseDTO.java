package com.eventmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketResponseDTO {
    private Long id;
    private String typeName;
    private double price;
    private int totalQuantity;
    private int soldQuantity;
    private int availableQuantity;

}

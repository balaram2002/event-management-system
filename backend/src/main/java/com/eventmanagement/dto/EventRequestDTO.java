package com.eventmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventRequestDTO {
    private String title;
    private String description;
    private LocalDate eventDate;
    private LocalTime eventTime;
    private String location;
}

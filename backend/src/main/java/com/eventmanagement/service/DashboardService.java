package com.eventmanagement.service;


import com.eventmanagement.dto.OrganizerDashboardDTO;

public interface DashboardService {

    OrganizerDashboardDTO getOrganizerDashboard(String organizerEmail);
}

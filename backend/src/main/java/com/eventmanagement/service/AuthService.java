package com.eventmanagement.service;


import com.eventmanagement.dto.AuthResponseDTO;
import com.eventmanagement.dto.LoginRequestDTO;
import com.eventmanagement.dto.RegisterRequestDTO;

public interface AuthService {

    AuthResponseDTO register(RegisterRequestDTO request);

    AuthResponseDTO login(LoginRequestDTO request);
}

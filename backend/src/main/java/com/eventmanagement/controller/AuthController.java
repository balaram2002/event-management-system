package com.eventmanagement.controller;

import com.eventmanagement.dto.AuthResponseDTO;
import com.eventmanagement.dto.LoginRequestDTO;
import com.eventmanagement.dto.RegisterRequestDTO;
import com.eventmanagement.service.AuthService;
import com.eventmanagement.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<?>> register(@RequestBody RegisterRequestDTO request) {

        AuthResponseDTO authResponse = authService.register(request);

        ApiResponse<AuthResponseDTO> response = ApiResponse.<AuthResponseDTO>builder()
                .success(true)
                .message("User registered successfully")
                .data(authResponse)
                .status(201)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(201).body(response);
    }


    @PostMapping("/login")
    public ResponseEntity<ApiResponse<?>> login(@RequestBody LoginRequestDTO request) {

        AuthResponseDTO authResponse = authService.login(request);

        ApiResponse<AuthResponseDTO> response = ApiResponse.<AuthResponseDTO>builder()
                .success(true)
                .message("Login successful")
                .data(authResponse)
                .status(200)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

}

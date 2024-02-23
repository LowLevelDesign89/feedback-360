package com.app.f360.controller;

import com.app.f360.models.dto.request.AuthRequestDTO;
import com.app.f360.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tokens")
public class TokenController {
    @Autowired
    private AuthService authService;

    @PostMapping
    public ResponseEntity<String> generateToken(@RequestBody AuthRequestDTO authRequestDTO) {
        String authToken = authService.authenticateAndGenerateToken(authRequestDTO);
        return new ResponseEntity<>(authToken, HttpStatus.OK);
    }
}

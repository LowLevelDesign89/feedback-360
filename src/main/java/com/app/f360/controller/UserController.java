package com.app.f360.controller;

import com.app.f360.models.dto.request.UserRegistrationRequestDTO;
import com.app.f360.models.dto.response.UserProfileResponseDTO;
import com.app.f360.models.dto.response.UserResponseDTO;
import com.app.f360.models.entity.User;
import com.app.f360.models.mappers.UserEntityDTOMapper;
import com.app.f360.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController{
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody UserRegistrationRequestDTO user) {
        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(UserEntityDTOMapper.toResponseDTO(createdUser),
                HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasAuthority('ROLE_EMPLOYEE') or hasAuthority('ROLE_MANAGER') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<UserProfileResponseDTO> viewProfile(@PathVariable String userId) {
        UserProfileResponseDTO responseDTO = userService.fetchUserProfile(userId);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

}

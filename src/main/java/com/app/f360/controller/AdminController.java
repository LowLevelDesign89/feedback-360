package com.app.f360.controller;

import com.app.f360.models.dto.request.UserManagerUpdateRequestDTO;
import com.app.f360.models.dto.request.UserRoleRequestDTO;
import com.app.f360.models.dto.response.UserResponseDTO;
import com.app.f360.models.dto.response.UserRoleResponseDTO;
import com.app.f360.models.entity.User;
import com.app.f360.models.mappers.UserEntityDTOMapper;
import com.app.f360.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@RequestMapping("/api/v1/users")
public class AdminController {
    @Autowired
    private AuthService authService;

    @PutMapping("/role")
    public ResponseEntity<UserRoleResponseDTO> addRoleToUser(@RequestBody UserRoleRequestDTO userRoleRequestDTO) {
        User user = authService.addRoleToUser(userRoleRequestDTO);
        return new ResponseEntity<>(UserEntityDTOMapper.toRoleResponseDTO(user), HttpStatus.OK);
    }


    @PutMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> deactivateUser(@PathVariable String userId) {
        User user = authService.deactivateUser(userId);
        return new ResponseEntity<>(UserEntityDTOMapper.toResponseDTO(user), HttpStatus.OK);
    }

    @PutMapping("/manager")
    public void updateUserManager(@RequestBody UserManagerUpdateRequestDTO requestDTO) {

    }
}

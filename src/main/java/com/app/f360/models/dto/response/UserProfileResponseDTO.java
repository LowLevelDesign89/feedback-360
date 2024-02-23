package com.app.f360.models.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class UserProfileResponseDTO {
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private UserResponseDTO manager;
    private List<UserResponseDTO> reportees;
}

package com.app.f360.models.dto.response;

import lombok.Data;

@Data
public class UserResponseDTO {
    private String userId;
    private String firstName;
    private String lastName;
    private boolean isActive;
}

package com.app.f360.models.dto.response;

import com.app.f360.models.enums.Role;
import lombok.Data;

import java.util.List;

@Data
public class UserRoleResponseDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private List<Role> roles;

    private boolean isActive;
}

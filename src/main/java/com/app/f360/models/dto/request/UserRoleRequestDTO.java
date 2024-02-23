package com.app.f360.models.dto.request;

import com.app.f360.models.enums.Role;
import lombok.Data;

import java.util.List;

@Data
public class UserRoleRequestDTO {
    private String userId;
    private List<Role> roles;
}

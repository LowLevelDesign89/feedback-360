package com.app.f360.service;

import com.app.f360.models.dto.request.AuthRequestDTO;
import com.app.f360.models.dto.request.UserRoleRequestDTO;
import com.app.f360.models.entity.User;

public interface AuthService {
    User addRoleToUser(UserRoleRequestDTO userRoleRequestDTO);

    User deactivateUser(String userId);

    String authenticateAndGenerateToken(AuthRequestDTO authRequestDTO);
}

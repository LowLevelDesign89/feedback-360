package com.app.f360.service;

import com.app.f360.models.dto.request.UserRegistrationRequestDTO;
import com.app.f360.models.dto.response.UserProfileResponseDTO;
import com.app.f360.models.entity.User;

public interface UserService {
    User createUser(UserRegistrationRequestDTO user);

    UserProfileResponseDTO fetchUserProfile(String userId);

    User assignReportee(String userId, String reporteeId);
}

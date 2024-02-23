package com.app.f360.models.mappers;

import com.app.f360.models.dto.response.UserProfileResponseDTO;
import com.app.f360.models.dto.response.UserResponseDTO;
import com.app.f360.models.dto.response.UserRoleResponseDTO;
import com.app.f360.models.entity.User;
import org.springframework.beans.BeanUtils;

import java.util.List;

public class UserEntityDTOMapper {
    public static UserResponseDTO toResponseDTO(User user) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        BeanUtils.copyProperties(user, userResponseDTO);
        return userResponseDTO;
    }

    public static UserRoleResponseDTO toRoleResponseDTO(User user) {
        UserRoleResponseDTO userRoleResponseDTO = new UserRoleResponseDTO();
        BeanUtils.copyProperties(user, userRoleResponseDTO);
        return userRoleResponseDTO;
    }

    public static UserProfileResponseDTO toProfileResponseDTO(User user) {
        UserProfileResponseDTO profileResponseDTO = new UserProfileResponseDTO();
        BeanUtils.copyProperties(user, profileResponseDTO);
        return profileResponseDTO;
    }

    public static List<UserResponseDTO> toResponseDTOs(List<User> users) {
        return users.stream()
                .map(UserEntityDTOMapper::toResponseDTO)
                .toList();
    }
}

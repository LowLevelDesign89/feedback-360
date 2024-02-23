package com.app.f360.service.impl;


import com.app.f360.models.dto.request.UserRegistrationRequestDTO;
import com.app.f360.models.dto.response.UserProfileResponseDTO;
import com.app.f360.models.entity.User;
import com.app.f360.models.enums.Role;
import com.app.f360.models.mappers.UserEntityDTOMapper;
import com.app.f360.repository.UserRepository;
import com.app.f360.service.UserService;
import com.app.f360.service.ValidationHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;


@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ValidationHelper validationHelper;

    @Override
    public User createUser(UserRegistrationRequestDTO user) {
        validationHelper.validateNewUser(user);
        User newUser = new User();
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        Set<Role> roles = new HashSet<>();
        roles.add(Role.ROLE_EMPLOYEE);
        newUser.setRoles(roles);
        newUser.setUserId(UUID.randomUUID().toString());
        newUser.setManagerId(user.getManangerId());
        newUser.setActive(true);
        userRepository.save(newUser);

        if(user.getManangerId() != null) {
            assignReportee(user.getManangerId(), newUser.getUserId());
        }
        return newUser;
    }

    @Override
    public UserProfileResponseDTO fetchUserProfile(String userId) {
        User user = validationHelper.checkUserExistence(userId);
        UserProfileResponseDTO userProfileResponseDTO = UserEntityDTOMapper.toProfileResponseDTO(user);
        User manager = null;
        if(user.getManagerId() != null) {
            manager = validationHelper.checkUserExistence(user.getManagerId());
            userProfileResponseDTO.setManager(UserEntityDTOMapper.toResponseDTO(manager));
        }
        List<User> reportees = new ArrayList<>();
        if(user.getReportees() != null && !user.getReportees().isEmpty()) {
            reportees = user.getReportees().stream()
                    .map(id -> validationHelper.checkUserExistence(id))
                    .filter(User::isActive)
                    .toList();
            userProfileResponseDTO.setReportees(UserEntityDTOMapper.toResponseDTOs(reportees));
        }
        return userProfileResponseDTO;
    }

    @Override
    public User assignReportee(String userId, String reporteeId) {
        User user = validationHelper.checkUserExistence(userId);
        validationHelper.checkUserExistence(reporteeId);


        if(!user.getReportees().contains(reporteeId)) {
            user.getReportees().add(reporteeId);
        }
        user.getRoles().add(Role.ROLE_MANAGER);
        userRepository.save(user);
        return user;
    }
}

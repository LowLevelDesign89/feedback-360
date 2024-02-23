package com.app.f360.service.impl;

import com.app.f360.common.ErrorKeyCodes;
import com.app.f360.exception.BadRequestException;
import com.app.f360.models.dto.request.AuthRequestDTO;
import com.app.f360.models.dto.request.UserRoleRequestDTO;
import com.app.f360.models.entity.User;
import com.app.f360.models.enums.Role;
import com.app.f360.repository.UserRepository;
import com.app.f360.service.AuthService;
import com.app.f360.service.JwtService;
import com.app.f360.service.ValidationHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ValidationHelper validationHelper;

    @Override
    public User addRoleToUser(UserRoleRequestDTO userRoleRequestDTO) {
        User retrivedUser = validationHelper.checkUserExistence(userRoleRequestDTO.getUserId());
        Set<Role> existingRoles = retrivedUser.getRoles();
        existingRoles.addAll(userRoleRequestDTO.getRoles());
        retrivedUser.setRoles(existingRoles);
        userRepository.save(retrivedUser);
        return retrivedUser;
    }

    @Override
    public User deactivateUser(String userId) {
        User retrievedUser = validationHelper.checkUserExistence(userId);
        retrievedUser.setActive(false);
        userRepository.save(retrievedUser);
        return retrievedUser;
    }

    @Override
    public String authenticateAndGenerateToken(AuthRequestDTO authRequestDTO) {
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword());
        Authentication authentication = authenticationManager.authenticate(authToken);
        if(authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequestDTO.getUsername());
        }
        throw new BadRequestException(ErrorKeyCodes.INCORRECT_CREDENTIALS);
    }
}

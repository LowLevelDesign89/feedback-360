package com.app.f360.service;


import com.app.f360.common.ErrorKeyCodes;
import com.app.f360.exception.NotFoundException;
import com.app.f360.exception.ValidationException;
import com.app.f360.models.dto.request.UserRegistrationRequestDTO;
import com.app.f360.models.entity.User;
import com.app.f360.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class ValidationHelper {
    @Autowired
    private UserRepository userRepository;

    public User checkUserExistence(String userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()) {
            log.error("User with ID: {} not exists ", userId);
            throw new NotFoundException(ErrorKeyCodes.USER_NOT_FOUND + ":" + userId);
        }
        return userOptional.get();
    }

    public void validateNewUser(UserRegistrationRequestDTO user) {
        Optional<User> userOptional = userRepository.findByEmail(user.getEmail());
        if(userOptional.isPresent()) {
            log.error("User with email: {} already exists", user.getEmail());
            throw new ValidationException(ErrorKeyCodes.USER_EMAIL_EXISTS + ":" + user.getEmail());
        }

        if(user.getManangerId() != null) {
            checkUserExistence(user.getManangerId());
        }
    }
}

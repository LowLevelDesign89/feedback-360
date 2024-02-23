package com.app.f360.service.security;

import com.app.f360.common.ErrorKeyCodes;
import com.app.f360.exception.NotFoundException;
import com.app.f360.models.entity.User;
import com.app.f360.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findById(username);
        if(userOptional.isEmpty()) {
            throw new NotFoundException(ErrorKeyCodes.USER_NOT_FOUND + ":" + username);
        }
        return new UserDetailsImpl(userOptional.get());
    }
}

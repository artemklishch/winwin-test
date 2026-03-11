package org.example.authapi.service.impl;

import jakarta.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import org.example.authapi.dto.user.UserRegistrationRequestDto;
import org.example.authapi.dto.user.UserResponseDto;
import org.example.authapi.exceptions.RegistrationException;
import org.example.authapi.model.User;
import org.example.authapi.repository.user.UserRepository;
import org.example.authapi.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserResponseDto register(final UserRegistrationRequestDto requestDto)
            throws RegistrationException {
        if (userRepository.existsByEmail(requestDto.getEmail())) {
            throw new RegistrationException(
                    "Can't register user with email " + requestDto.getEmail() + ". Email is already in use.");
        }
        User user =
                new User()
                        .setEmail(requestDto.getEmail())
                        .setPasswordHash(passwordEncoder.encode(requestDto.getPassword()));
        userRepository.save(user);
        return UserResponseDto.builder().id(user.getId()).email(user.getEmail()).build();
    }
}

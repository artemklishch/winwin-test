package org.example.authapi.service;

import org.example.authapi.dto.user.UserRegistrationRequestDto;
import org.example.authapi.dto.user.UserResponseDto;
import org.example.authapi.exceptions.RegistrationException;

public interface UserService {
  UserResponseDto register(UserRegistrationRequestDto requestDto) throws RegistrationException;
}

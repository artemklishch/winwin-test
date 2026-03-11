package org.example.authapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.authapi.dto.user.UserLoginRequestDto;
import org.example.authapi.dto.user.UserLoginResponseDto;
import org.example.authapi.dto.user.UserRegistrationRequestDto;
import org.example.authapi.dto.user.UserResponseDto;
import org.example.authapi.exceptions.RegistrationException;
import org.example.authapi.security.AuthenticationService;
import org.example.authapi.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService authenticationService;
  private final UserService userService;

  @PostMapping("/register")
  public UserResponseDto register(@RequestBody @Valid UserRegistrationRequestDto requestDto)
      throws RegistrationException {
    return userService.register(requestDto);
  }

  @PostMapping("/login")
  public UserLoginResponseDto login(
          @RequestBody @Valid UserLoginRequestDto requestDto
  ) {
    return authenticationService.authenticate(requestDto);
  }
}

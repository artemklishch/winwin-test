package org.example.authapi.security;

import lombok.RequiredArgsConstructor;
import org.example.authapi.dto.user.UserLoginRequestDto;
import org.example.authapi.dto.user.UserLoginResponseDto;
import org.example.authapi.model.User;
import org.example.authapi.repository.user.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final JwtUtil jwtUtil;

  private final AuthenticationManager authenticationManager;

  private final UserRepository userRepository;

  public UserLoginResponseDto authenticate(UserLoginRequestDto requestDto) {
    final Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(requestDto.email(), requestDto.password()));
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
      assert userDetails != null;
      User user = userRepository.findByEmail(userDetails.getUsername())
            .orElseThrow(() -> new RuntimeException("User not found"));
    String token = jwtUtil.generateToken(user.getEmail());
    return new UserLoginResponseDto(token);
  }
}

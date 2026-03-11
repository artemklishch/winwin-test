package org.example.authapi.security;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.authapi.repository.user.UserRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(final @NonNull String email)
      throws UsernameNotFoundException {
    return userRepository
        .findByEmail(email)
        .map(
            user ->
                org.springframework.security.core.userdetails.User.builder()
                    .username(user.getEmail())
                    .password(user.getPasswordHash())
                    .authorities(List.of())
                    .build())
        .orElseThrow(() -> new UsernameNotFoundException("Can't find user by email: " + email));
  }
}

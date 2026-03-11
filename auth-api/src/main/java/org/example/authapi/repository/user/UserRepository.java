package org.example.authapi.repository.user;

import java.util.Optional;
import java.util.UUID;
import org.example.authapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UUID> {
  boolean existsByEmail(String email);

  Optional<User> findByEmail(final String email);
}

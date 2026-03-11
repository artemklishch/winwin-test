package org.example.authapi.model;

import jakarta.persistence.*;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Entity
@Table(name = "users")
@Accessors(chain = true)
public class User {
  @Id @GeneratedValue private UUID id;

  @Column(name = "email")
  private String email;

  @Column(name = "password_hash")
  private String passwordHash;
}

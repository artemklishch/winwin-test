package org.example.authapi.dto.user;

import java.util.UUID;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Builder
public class UserResponseDto {
  private UUID id;
  private String email;
}

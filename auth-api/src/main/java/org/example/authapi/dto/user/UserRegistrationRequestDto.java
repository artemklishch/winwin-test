package org.example.authapi.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.Accessors;
import org.example.authapi.validation.FieldMatch;
import org.hibernate.validator.constraints.Length;

@Data
@FieldMatch(field = "password", verifyField = "repeatPassword")
@Accessors(chain = true)
public class UserRegistrationRequestDto {
  @NotBlank(message = "Email field is mandatory")
  @Email(message = "Should be a valid email.")
  @Length(max = 255, message = "Email can not be over 255 characters")
  private String email;

  @NotBlank(message = "Password field is mandatory")
  @Length(min = 8, max = 35, message = "Password length can be from 8 to 35 characters")
  private String password;

  @NotBlank(message = "Repeat password field is mandatory")
  @Length(min = 8, max = 35, message = "Password length can be from 8 to 35 characters")
  private String repeatPassword;
}

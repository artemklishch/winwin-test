package org.example.authapi.dto.process;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;

@Builder
public record ProcessContentDto(
        @NotBlank(message = "Email is mandatory")
        @Length(
                min = 3,
                max = 20,
                message = "text must be at leats 3 and no more than 20 characters")
        String text
) {
}

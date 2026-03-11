package org.example.dataapi.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record TransformReqDto(
        @NotBlank(message = "Email is mandatory")
        @Length(
                min = 3,
                max = 20,
                message = "text must be at leats 3 and no more than 20 characters")
        String text
) {
}

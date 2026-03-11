package org.example.authapi.dto.process;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ProcessingLogResponseDto {
    private UUID id;
    private String inputText;
    private String outputText;
    private UUID userId;
}

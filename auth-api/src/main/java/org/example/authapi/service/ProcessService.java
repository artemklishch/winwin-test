package org.example.authapi.service;

import org.example.authapi.dto.process.ProcessContentDto;
import org.example.authapi.dto.process.ProcessResultDto;
import org.example.authapi.dto.process.ProcessingLogResponseDto;

public interface ProcessService {
    ProcessingLogResponseDto process(final ProcessContentDto content, final String email);

    ProcessingLogResponseDto saveProcessingLog(
            final ProcessContentDto content, final ProcessResultDto result, final String email
    );
}

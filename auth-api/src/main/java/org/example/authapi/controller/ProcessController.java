package org.example.authapi.controller;

import lombok.RequiredArgsConstructor;
import org.example.authapi.dto.process.ProcessContentDto;
import org.example.authapi.dto.process.ProcessingLogResponseDto;
import org.example.authapi.service.ProcessService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/process")
@RequiredArgsConstructor
public class ProcessController {
    private final ProcessService processService;

    @PostMapping
    public ProcessingLogResponseDto process(
            final @RequestBody ProcessContentDto processContentDto, final Authentication authentication
    ) {
        String email = authentication.getName();
        return processService.process(processContentDto, email);
    }
}

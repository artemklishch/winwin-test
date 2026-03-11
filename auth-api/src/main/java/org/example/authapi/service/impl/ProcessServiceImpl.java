package org.example.authapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.authapi.dto.process.ProcessContentDto;
import org.example.authapi.dto.process.ProcessResultDto;
import org.example.authapi.dto.process.ProcessingLogResponseDto;
import org.example.authapi.exceptions.ProceedingException;
import org.example.authapi.model.ProcessingLog;
import org.example.authapi.model.User;
import org.example.authapi.repository.user.ProcessRepository;
import org.example.authapi.repository.user.UserRepository;
import org.example.authapi.service.ProcessService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ProcessServiceImpl implements ProcessService {
    private final RestTemplate restTemplate;
    private final UserRepository userRepository;
    private final ProcessRepository processRepository;

    @Value("${internal.token}")
    private String internalToken;

    @Value("${data-api.url}")
    private String dataApiUrl;

    @Override
    public ProcessingLogResponseDto process(final ProcessContentDto content, final String email) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Internal-Token", internalToken);

        HttpEntity<ProcessContentDto> request = new HttpEntity<>(content, headers);

        ResponseEntity<ProcessResultDto> response =
                restTemplate.postForEntity(
                        dataApiUrl + "/api/transform",
                        request,
                        ProcessResultDto.class
                );
        if (response.getBody() == null) {
            throw new ProceedingException("Data is not transformed");
        }
        return saveProcessingLog(content, response.getBody(), email);
    }

    @Override
    public ProcessingLogResponseDto saveProcessingLog(
            final ProcessContentDto content, final ProcessResultDto result, final String email
    ) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ProceedingException("User not found"));
        ProcessingLog processingLog = new ProcessingLog();
        processingLog.setUser(user);
        processingLog.setInputText(content.text());
        processingLog.setOutputText(result.result());
        processRepository.save(processingLog);

        return ProcessingLogResponseDto.builder()
                .id(processingLog.getId())
                .inputText(processingLog.getInputText())
                .outputText(processingLog.getOutputText())
                .userId(processingLog.getUser().getId())
                .build();
    }
}

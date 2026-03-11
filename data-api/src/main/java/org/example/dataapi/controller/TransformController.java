package org.example.dataapi.controller;

import lombok.RequiredArgsConstructor;
import org.example.dataapi.dto.TransformReqDto;
import org.example.dataapi.dto.TransformResDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transform")
@RequiredArgsConstructor
public class TransformController {
    @Value("${internal.token}")
    private String internalToken;

    @PostMapping
    public ResponseEntity<TransformResDto> transform(
            final @RequestHeader("X-Internal-Token") String token,
            final @RequestBody TransformReqDto request
    ) {
        if (!token.equals(internalToken)) {
            return ResponseEntity.status(403).build();
        }

        String result = new StringBuilder(request.text())
                .reverse()
                .toString()
                .toUpperCase();

        return ResponseEntity.ok(TransformResDto.builder().result(result).build());
    }
}

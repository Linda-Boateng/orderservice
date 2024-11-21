package com.example.orderservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@AllArgsConstructor
@Builder
@Data
public class ErrorResponseDto {
    private String message;
    private String path;
    private final OffsetDateTime timeStamp = OffsetDateTime.now();
}

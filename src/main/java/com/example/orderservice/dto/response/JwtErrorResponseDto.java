package com.example.orderservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class JwtErrorResponseDto {
  private String message;
  private String error;
}

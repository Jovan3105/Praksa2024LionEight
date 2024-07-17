package com.example.demo.dtos;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CredentialsDto {
  private String username;
  private String password;
}

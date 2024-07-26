package com.example.demo.controllers.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SkyonicsRequestGet {
    private String token;
    private String APIKey;
}

package com.example.demo.controllers.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
public class SkyonicsRequestPost {
    @JsonProperty("APIKey")
    private String APIKey;
    private String serialNumber;
    private int mode;
    private String command;
}

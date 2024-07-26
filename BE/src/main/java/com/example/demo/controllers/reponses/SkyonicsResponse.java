package com.example.demo.controllers.reponses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SkyonicsResponse {
    @JsonProperty("Token")
    private String Token;
    @JsonProperty("CommandSentTime")
    private String CommandSentTime;
    @JsonProperty("Expiration")
    private String Expiration;
    @JsonProperty("SerialNumber")
    private String SerialNumber;
    @JsonProperty("CommandString")
    private String CommandString;
    @JsonProperty("ResultInfo")
    private String ResultInfo;
    @JsonProperty("Status")
    private String Status;
    @JsonProperty("SentToAddress")
    private String SentToAddress;
    @JsonProperty("HandledBy")
    private String HandledBy;

}

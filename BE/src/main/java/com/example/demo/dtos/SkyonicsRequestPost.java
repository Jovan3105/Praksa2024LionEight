package com.example.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SkyonicsRequestPost {
    private String APIKey;
    private String serialNumber;
    private int mode;
    private String command;
}

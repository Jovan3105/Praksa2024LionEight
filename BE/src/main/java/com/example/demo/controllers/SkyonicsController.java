package com.example.demo.controllers;

import com.example.demo.services.SkyonicsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SkyonicsController {

    @Autowired
    private SkyonicsService skyonicsService;

    @PostMapping
    public String touchEndpoint(String command,String deviceNumber){
        //return skyonicsService.touchEndpoint(command,deviceNumber);
        return "";
    }
}
//
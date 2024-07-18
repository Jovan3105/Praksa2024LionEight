package com.example.demo.controllers;

import com.example.demo.dtos.SkyonicsRequest;
import com.example.demo.services.SkyonicsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class SkyonicsController {

    @Autowired
    private final SkyonicsService skyonicsService;

    @PostMapping("/api/setCommand")
    public Mono<String> touchEndpoint(@RequestBody SkyonicsRequest request){

        return skyonicsService.touchEndpoint(request);
    }
}
//
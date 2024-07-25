package com.example.demo.controllers;


import com.example.demo.dtos.SkyonicsRequestGet;
import com.example.demo.dtos.SkyonicsRequestPost;
import com.example.demo.dtos.SkyonicsResponse;
import com.example.demo.services.SkyonicsService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SkyonicsController {

    private static final String[] commands = {"\"DIAG CAN\"","\"SHIPMODE\"", "\"SETPARAMS 527=5\"", "\"SETPARAMS 527=4\"", "\"SETPARAMS 527=3\"", "\"SETPARAMS 527=2\""};
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final SkyonicsService skyonicsService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/deviceCommand")
    public ResponseEntity<SkyonicsResponse> deviceCommand(@RequestBody SkyonicsRequestPost request) throws InterruptedException {

        if (Arrays.stream(commands).noneMatch(com->com.equals(request.getCommand()))){
            SkyonicsResponse response = new SkyonicsResponse();
            response.setResultInfo("Command does not exists");
            return ResponseEntity.badRequest().body(response);
        }

        String token = skyonicsService.deviceCommandPOST(request);
        String[] s = token.split("\"");
        token = s[1];
        Thread.sleep(1000);
        return ResponseEntity.ok(skyonicsService.deviceCommandGET(new SkyonicsRequestGet(token,request.getAPIKey())));

    }


}
//
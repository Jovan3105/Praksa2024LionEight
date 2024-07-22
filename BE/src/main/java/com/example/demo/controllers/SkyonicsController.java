package com.example.demo.controllers;


import com.example.demo.dtos.SkyonicsRequestGet;
import com.example.demo.dtos.SkyonicsRequestPost;
import com.example.demo.services.SkyonicsService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@RestController
@RequiredArgsConstructor
public class SkyonicsController {

    private static final String[] commands = {"\"DIAG CAN\"","\"SHIPMODE\"", "\"SETPARAMS 527=5\"", "\"SETPARAMS 527=4\"", "\"SETPARAMS 527=3\"", "\"SETPARAMS 527=2\""};
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private SkyonicsService skyonicsService;

    @PostMapping("/api/deviceCommand")
    public ResponseEntity<Mono<JsonNode>> deviceCommand(@RequestBody SkyonicsRequestPost request) throws InterruptedException {

        //return skyonicsService.deviceCommandPOST(request);
        if (Arrays.stream(commands).noneMatch(com->com.equals(request.getCommand()))){
            ObjectNode jsonNode = objectMapper.createObjectNode();
            jsonNode.put("ResultInfo","Command does not exists");
            jsonNode.put("status",HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(Mono.just(jsonNode),HttpStatus.OK);
        }

        Mono<String> ms = skyonicsService.deviceCommandPOST(request);
        String token = ms.block();
        String[] s = token.split("\"");
        token = s[1];
        Thread.sleep(1000);
        return new ResponseEntity<>(skyonicsService.deviceCommandGET(new SkyonicsRequestGet(token,request.getAPIKey())),HttpStatus.OK);

    }

}
//
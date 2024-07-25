package com.example.demo.controllers;


import com.example.demo.dtos.SkyonicsRequestGet;
import com.example.demo.dtos.SkyonicsRequestPost;
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
    public JsonNode deviceCommand(@RequestBody SkyonicsRequestPost request) throws InterruptedException {

        //return skyonicsService.deviceCommandPOST(request);
        System.out.println(request.getAPIKey()+" " + request.getCommand() + " " +request.getSerialNumber());
        if (Arrays.stream(commands).noneMatch(com->com.equals(request.getCommand()))){
            ObjectNode jsonNode = objectMapper.createObjectNode();
            jsonNode.put("ResultInfo","Command does not exists");
            jsonNode.put("status",HttpStatus.BAD_REQUEST.value());
            return jsonNode;
        }
        Mono<String> ms = skyonicsService.deviceCommandPOST(request);
        String token = ms.block();
        String[] s = token.split("\"");
        token = s[1];
        Thread.sleep(1000);
        return skyonicsService.deviceCommandGET(new SkyonicsRequestGet(token,request.getAPIKey()));

    }


}
//
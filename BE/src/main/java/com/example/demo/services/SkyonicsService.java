package com.example.demo.services;

import com.example.demo.dtos.SkyonicsRequestGet;
import com.example.demo.dtos.SkyonicsRequestPost;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class SkyonicsService {
    private static String BASEURL = "https://www.skyonics.net/";
    private final WebClient webClient;
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    public SkyonicsService(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.baseUrl(BASEURL).build();
    }
    public Mono<String> deviceCommandPOST(SkyonicsRequestPost request){
        try{
            return webClient.post()
                    .uri(uriBuilder -> uriBuilder
                            .path("api/skyonics/devicecommand")
                            .queryParam("APIKey",request.getAPIKey())
                            .queryParam("serialNumber",request.getSerialNumber())
                            .queryParam("mode",request.getMode())
                            .build()

                    )
                    .header("Content-Type","application/json")
                    .header("Accept-Encoding","gzip, deflate, br")
                    .header("Accept","application/json")
                    .body(Mono.just(request.getCommand()), String.class)
                    .retrieve()
                    .bodyToMono(String.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public JsonNode deviceCommandGET(@RequestBody SkyonicsRequestGet request){
        try{
            return webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("api/skyonics/devicecommand")
                            .queryParam("APIKey",request.getAPIKey())
                            .queryParam("token",request.getToken())
                            .build()

                    )
                    .header("Content-Type","application/json")
                    .header("Accept-Encoding","gzip, deflate, br")
                    .header("Accept","application/json")
                    .retrieve()
                    .bodyToMono(String.class)
                    .flatMap(
                            response->{
                                try{
                                    JsonNode originalJson = objectMapper.readTree(response);
                                    ObjectNode extended = (ObjectNode) originalJson;
                                    extended.put("status",HttpStatus.OK.value());
                                    return Mono.just(extended);
                                } catch (JsonProcessingException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                    )
                    .block();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

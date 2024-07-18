package com.example.demo.services;

import com.example.demo.dtos.CommandRequest;
import com.example.demo.dtos.SkyonicsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

@Service
public class SkyonicsService {
    private static String BASEURL = "https://www.skyonics.net/";
    private final WebClient webClient;
    @Autowired
    public SkyonicsService(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.baseUrl(BASEURL).build();
    }
    public Mono<String> touchEndpoint(SkyonicsRequest request){
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
}

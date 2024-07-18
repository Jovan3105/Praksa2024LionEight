package com.example.demo.services;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

@Service
public class SkyonicsService {
    private static String URL = "https://skyonics.net/api/skyonics/devicecommand";
    public String touchEndpoint(String APIKey,String serialNumber,String command){
        try{
            HttpClient client = HttpClient.newHttpClient();
            URI uri = new URI(URL);
            String json = "{\"APIKey\":\""+APIKey+"\",\"command\":\""+command+"\",\"serialNumber\":\""+serialNumber+"\"}";
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .header("Content-Type","application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json, StandardCharsets.UTF_8))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();


        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

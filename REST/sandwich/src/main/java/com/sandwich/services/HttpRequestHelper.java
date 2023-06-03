package com.sandwich.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sandwich.dtos.IngredientResponseDTO;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.UUID;

public class HttpRequestHelper {

    public boolean doesIngredientExist(String name) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8081/ingredient/existence/"+ name ))
                .build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper objectMapper = new ObjectMapper();
        String body = response.body().toString();
        boolean result = false;
        if (body.equals("true")){
            result=true;
        }
        else if(body.equals("false")){
            result=false;
        }
        return result;
    }


    public IngredientResponseDTO IngredientByKey(UUID ingredientKey) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8080/ingredient/getToSend/"+ ingredientKey ))
                .build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper objectMapper = new ObjectMapper();
        String body = response.body().toString();
        var result = objectMapper.readValue(body, IngredientResponseDTO.class);
        return result;
    }
}

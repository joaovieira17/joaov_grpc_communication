package com.ingredient.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ingredient.dtos.CategoryResponseDTO;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpRequestHelper {

    public CategoryResponseDTO CategoryByKey(String categoryKey) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8090/category/getByKey/"+ categoryKey ))
                .build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper objectMapper = new ObjectMapper();
        String body = response.body().toString();
        var result = objectMapper.readValue(body, CategoryResponseDTO.class);
        return result;
    }
}

package com.reviews.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reviews.model.Product;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpRequestHelper {

    public Product getProduct(String sku) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8083/product?sku=" + sku))
                .build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper objectMapper = new ObjectMapper();
        String body = response.body().toString();
        var result = objectMapper.readValue(body, Product.class);
        return result;
    }

    public boolean doesProductExist1(String sku) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8083/product/"+ sku +"/productExistence" ))
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

    public boolean doesProductExist2(String sku) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8087/product/"+ sku +"/productExistence" ))
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


}

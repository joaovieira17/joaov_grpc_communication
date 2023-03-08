package com.products.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.products.model.Catalog;
import com.products.model.Product;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class HttpRequestHelper {

    public Product getProduct(String sku) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8087/product/productBySku/" + sku))
                .build();

        HttpResponse response = client.send(request,
                HttpResponse.BodyHandlers.ofString());


        ObjectMapper objectMapper = new ObjectMapper();
        String body = response.body().toString();
        var result = objectMapper.readValue(body, Product.class);
        return result;
    }

    public List<Product> getProductBySkuOrDesignation(String skuOrDesignation) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8087/product/searchSemPage/" + skuOrDesignation ))
                .build();

        HttpResponse response = client.send(request,
                HttpResponse.BodyHandlers.ofString());


        /*ObjectMapper objectMapper = new ObjectMapper();
        List<String> body = Collections.singletonList(response.body().toString());

        String jsonArray = objectMapper.writeValueAsString(Collections.singletonList(response.body().toString()));


        List<Product> asList = objectMapper.readValue(jsonArray, List.class);

        return asList;*/


        ObjectMapper objectMapper = new ObjectMapper();
        String body = response.body().toString();
        var result = objectMapper.readValue(body, ArrayList.class);
        return result;
    }

    public boolean doesProductExist(String sku) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8087/product/"+sku+"/productExistence"))
                .build();

        HttpResponse response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        ObjectMapper objectMapper = new ObjectMapper();
        String body = response.body().toString();
        if (body.equals("true")){
            return true;
        }
        else {
            return false;
        }
    }


    public List<Catalog> getCatalog() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8087/product/catalog" ))
                .build();

        HttpResponse response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        /*ObjectMapper objectMapper = new ObjectMapper();
        List<String> body = Collections.singletonList(response.body().toString());

        String jsonArray = objectMapper.writeValueAsString(Collections.singletonList(response.body().toString()));

        List<Catalog> asList = objectMapper.readValue(jsonArray, List.class);

        return asList;*/

        ObjectMapper objectMapper = new ObjectMapper();
        String body = response.body().toString();
        var result = objectMapper.readValue(body, ArrayList.class);
        return result;
    }
}

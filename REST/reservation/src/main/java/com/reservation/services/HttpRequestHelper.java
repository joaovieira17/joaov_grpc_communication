package com.reservation.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.UUID;

public class HttpRequestHelper {

    public boolean doesSandwichExist(String sandwichPublicKey) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8081/sandwich/existenceByKey/"+ sandwichPublicKey ))
                .build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());

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

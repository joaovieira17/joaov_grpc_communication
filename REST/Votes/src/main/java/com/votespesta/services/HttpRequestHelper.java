package com.votespesta.services;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.UUID;

public class HttpRequestHelper {

    public boolean reviewExistence(UUID reviewId) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8084/review/"+reviewId+"/reviewExistence"))
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

    public void upVoteReview(UUID reviewId) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8084/review/upvote/" + reviewId))
                .PUT(HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.discarding());

    }

    public void downVoteReview(UUID reviewId) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8084/review/downvote/" + reviewId))
                .PUT(HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.discarding());

    }

}

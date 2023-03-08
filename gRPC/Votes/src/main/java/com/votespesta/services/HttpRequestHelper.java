package com.votespesta.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.votespesta.model.Review;
import com.votespesta.model.Vote;
import com.votespesta.security.JwtResponse;
import org.apache.commons.lang3.mutable.MutableBoolean;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;
import java.util.UUID;

public class HttpRequestHelper {

    public Review getReview(UUID reviewId) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8084/review/" + reviewId))
                .build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper objectMapper = new ObjectMapper();
        String body = response.body().toString();
        if (!Objects.equals(body, "") && response.statusCode()!=404) {
            var result = objectMapper.readValue(body, Review.class);
            return result;
        }
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Review Not Found");
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


    /*public Vote newVote() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString("Sample request body"))
                .uri(URI.create("http://localhost:8084/review?reviewId=" + reviewId))
                .build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper objectMapper = new ObjectMapper();
        String body = response.body().toString();
        var result = objectMapper.readValue(body, Review.class);
        return result;
    }*/

JwtResponse jwtResponse;

    public boolean doesVoteExist(UUID reviewId) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8089/vote/getVote?reviewId="+reviewId))
                .header("Authorization","Bearer "+ "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBbGJlcnQiLCJpYXQiOjE2Njg2OTE1ODUsImV4cCI6MTY2ODc3Nzk4NX0.GyF7TkX6nRYQ5bErFXlLVdVh9HXOsi9tubuGaEFuHcw7o5qN9BzixHn9rtkekPNUTuQWhR6R2bauvW9FGKLS8w" )
                .build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper objectMapper = new ObjectMapper();
        String body = response.body().toString();

        //boolean result1 = true;

        MutableBoolean result1 = new MutableBoolean();
        if (body.equals("true")){
            result1.setValue(true);
        }
        else result1.setValue(false);
        /*boolean result=result1;
        return result;*/

        //var result = objectMapper.readValue(body, boolean.class);
        //return result;
boolean result=result1.getValue();
return result;
    }


    public Vote getVoteData(UUID reviewId) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8089/vote/getVoteData?reviewId=" + reviewId))
                .build();

        HttpResponse response = client.send(request,
                HttpResponse.BodyHandlers.ofString());


        ObjectMapper objectMapper = new ObjectMapper();
        String body = response.body().toString();
        var result = objectMapper.readValue(body, Vote.class);
        return result;
    }
}

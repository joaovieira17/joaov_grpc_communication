package com.reviewspesta.repositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reviewspesta.model.Review;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HttpReviewRepository {
    /*public Review getReview(UUID reviewId) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8088/review?reviewId=" + reviewId.toString()))
                .build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper objectMapper = new ObjectMapper();
        String body = response.body().toString();
        var result = objectMapper.readValue(body, Review.class);
        return result;
    }

    public boolean doesReviewExist(UUID reviewId) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8088/review/" + reviewId.toString() + "/reviewExistence"))
                .build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper objectMapper = new ObjectMapper();
        String body = response.body().toString();
        if (body.equals("true")){
            return true;
        }
        else{
            return false;
        }
    }

    public List<Review> getReviewsOrderByDate(String sku) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8088/review/" + sku + "/date/here/local"))
                .build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper objectMapper = new ObjectMapper();
        String body = response.body().toString();
        var result = objectMapper.readValue(body, ArrayList.class);
        return result;
    }

    public List<Review> getReviewsOrderByVotes(String sku) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8088/review/" + sku + "/date/here/local"))
                .build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper objectMapper = new ObjectMapper();
        String body = response.body().toString();
        var result = objectMapper.readValue(body, ArrayList.class);
        return result;
    }

    public List<Review> getReviewsByProduct(String sku) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8088/review/" + sku))
                .build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper objectMapper = new ObjectMapper();
        String body = response.body().toString();
        var result = objectMapper.readValue(body, ArrayList.class);
        return result;
    }

    public List<Review> getAllPendingReviews() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8088/review/pendingLocal"))
                .header("Authorization","Bearer "+"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBbGJlcnQiLCJpYXQiOjE2NjkxNTI2MzUsImV4cCI6MTY2OTIzOTAzNX0.IOLxJlnuZ3LpV0vpBdT5PVfS1SxPjpj1iMyNrY5buZcqfvml0H4Ss9t1VdDH2RfuSIuSCcNedCxZ8Sfr2hJxmw")
                .build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper objectMapper = new ObjectMapper();
        String body = response.body().toString();
        var result = objectMapper.readValue(body, ArrayList.class);
        return result;
    }

    public List<Review> getMyReviews() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:8088/review/myLocalReview"))
                .header("Authorization","Bearer "+"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBbGJlcnQiLCJpYXQiOjE2NjkxNTUyNTIsImV4cCI6MTY2OTI0MTY1Mn0.cs0Ag39bWL_92TIb2DRkB5mdl1HxlXSwnVG3TYD0AYfQGQQ6h-eMXbTexW_NkxiYaWCJewTObUvZm3BrN9duOA")
                .build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper objectMapper = new ObjectMapper();
        String body = response.body().toString();
        var result = objectMapper.readValue(body, ArrayList.class);
        return result;
    }


    public String approveRejectReview(UUID reviewId, boolean status)throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .PUT(HttpRequest.BodyPublishers.ofString("Sample request body"))
                .uri(URI.create("http://localhost:8088/review/" + reviewId.toString() + "/approve/"+ status))
                .header("Authorization","Bearer "+"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBbGJlcnQiLCJpYXQiOjE2NjkxNTI2MzUsImV4cCI6MTY2OTIzOTAzNX0.IOLxJlnuZ3LpV0vpBdT5PVfS1SxPjpj1iMyNrY5buZcqfvml0H4Ss9t1VdDH2RfuSIuSCcNedCxZ8Sfr2hJxmw")
                .build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper objectMapper = new ObjectMapper();
        String body = response.body().toString();
        //var result = objectMapper.readValue(body, String.class);
        //return result;
        return body;
    }

    public String deleteReview(UUID reviewId)throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .DELETE()
                .uri(URI.create("http://localhost:8088/review/" + reviewId.toString() + "/remove"))
                .header("Authorization","Bearer "+"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBbGJlcnQiLCJpYXQiOjE2NjkxNTI2MzUsImV4cCI6MTY2OTIzOTAzNX0.IOLxJlnuZ3LpV0vpBdT5PVfS1SxPjpj1iMyNrY5buZcqfvml0H4Ss9t1VdDH2RfuSIuSCcNedCxZ8Sfr2hJxmw")
                .build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper objectMapper = new ObjectMapper();
        String body = response.body().toString();
        return body;
    }*/
}

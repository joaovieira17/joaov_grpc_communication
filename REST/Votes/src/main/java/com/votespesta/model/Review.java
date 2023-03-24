package com.votespesta.model;

import org.hibernate.annotations.Type;


import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;


public class Review {

    private UUID reviewId = UUID.randomUUID();

    private String status;


    private Review(final UUID reviewId,final String status) {
        this.reviewId=reviewId;
        this.status=status;
    }

    public Review() {
    }


    public UUID getReviewId() {
        return reviewId;
    }


    public String getStatus(){return status;}

    public void setStatus(final String status){
        if(status == null || status.isEmpty()){
            throw new IllegalArgumentException("Status is a mandatory attribute");
        }
        this.status = status;
    }



}


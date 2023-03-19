package com.reviewspesta.model;

import com.github.pemistahl.lingua.api.*;
import static com.github.pemistahl.lingua.api.Language.*;
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


@Entity
public class Review {

    @Id
    @Column(name = "reviewdId")
    @Type(type = "uuid-char")
    private UUID reviewId = UUID.randomUUID();

    @Column(nullable = true)
    @Size(max = 2048)
    private String text;

    //@CreationTimestamp
    //@Generated(GenerationTime.ALWAYS)
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private String status;

    @Column(nullable = true)
    private int rating;

    @Column(nullable = true)
    private int upVotes;

    @Column(nullable = true)
    private int downVotes;

    @Column(nullable = false)
    private String funFact;

    @Column(nullable = true)
    private Long userId;

    @Column(nullable = true)
    private String productSku;

    @Column(nullable = true)
    private String language;

    private Review(final UUID reviewId,final String status,final Date date, final String text) {
        setStatus(status);
        setDate(date);
        setText(text);
    }

    private Review(final UUID reviewId,final String status,final Date date, final String text, final int rating, final int upVotes, final int downVotes, final String funFact, final Long userId,final String productSku, final String language) {
        this(reviewId,status,date, text);
        setRating(rating);
        setDownVotes(downVotes);
        setUpVotes(upVotes);
        getFunFactResponse(date);
        setUserId(userId);
        setProductSku(productSku);
        setLanguage(language);
    }

    public Review() {
    }


    public String getText() {
        return text;
    }

    public UUID getReviewId() {
        return reviewId;
    }

    public void setText(final String text) {
        if (text.length()>2048){
            throw new IllegalArgumentException("Review Text Length is too big");
        }
        if (text.trim().length()==0){
            throw new IllegalArgumentException("Review Text cannot be white spaces");
        }
        this.text = text;
    }

    public int getRating(){
        return rating;
    }

    public void setRating(int rating){
        if (rating<0 || rating>5 ){
            throw new IllegalArgumentException("Rating out of range");
        }
        this.rating = rating;
    }

    public Date getDate(){return date;}

    public void setDate(Date date) {
        if (date == null){
            throw new IllegalArgumentException("Date is a mandatory attribute");
        }
        this.date = date;
    }

    public String getStatus(){return status;}

    public void setStatus(final String status){
        if(status == null || status.isEmpty()){
            throw new IllegalArgumentException("Status is a mandatory attribute");
        }
        this.status = status;
    }

    public int getUpVotes() {
        return upVotes;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUpVotes(int upVotes) {
        this.upVotes = upVotes;
    }

    public int getDownVotes() {
        return downVotes;
    }

    public void setDownVotes(int downVotes) {
        this.downVotes = downVotes;
    }


    public void updateVote(boolean voteValue){
        if(!voteValue){
            this.downVotes = getDownVotes() +1;
        }else{
            this.upVotes = getUpVotes() +1;
        }
    }

    public Boolean goodToDel(int upVotes, int  downVotes){
        return upVotes == 0 && downVotes == 0;
    }

    public String getProductSku() {
        return productSku;
    }

    public void setProductSku(String productSku) {
        this.productSku = productSku;
    }

    public String getFunFact() {
        return funFact;
    }

    public void setFunFact(String funFact) {
        if (funFact == null || funFact.isEmpty()){
            throw new IllegalArgumentException("Fun Fact Empty");
        }
        this.funFact = funFact;
    }

    public void getFunFactResponse(Date date) {
        String response;
        String finalResponse;
        String[] parts;
        String part1;
        int dot;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1;
        String header = "Basic XXXXX";
        try {
            URL url = new URL("http://numbersapi.com/" + month + "/" + day + "/date");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("authorization",header);
            connection.setRequestProperty("Content-Type", "application/json");
            BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bf.readLine()) != null )
            {
                stringBuilder.append(line);
            }
            response = stringBuilder.toString();
            finalResponse = response.substring(11);
            parts = finalResponse.split("\\.\",");
            part1 = parts[0];
            setFunFact(part1);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public static Review newFrom(final ReviewDTO rev, final String productSku, final Long userId) {
        final Review obj = new Review();
        long millis = System.currentTimeMillis();
        final LanguageDetector detector = LanguageDetectorBuilder.fromLanguages(ENGLISH, FRENCH, GERMAN, SPANISH, PORTUGUESE).build();
        if(!rev.getText().isEmpty() || rev.getRating() != 0){
            obj.status = "PENDING";
            obj.upVotes = 0;
            obj.downVotes = 0;
            obj.date = new Date(millis);
            obj.rating = rev.rating;
            obj.text = rev.text;
            obj.getFunFactResponse(obj.date);
            //obj.productSku=sku;
            obj.language=detector.detectLanguageOf(rev.text).toString();
            return new Review(obj.reviewId, obj.status, obj.date, obj.text, obj.rating, obj.upVotes, obj.downVotes, obj.funFact, userId,productSku,obj.language);
        }
        else{
            return obj;
        }
    }

}


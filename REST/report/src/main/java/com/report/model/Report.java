package com.report.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.UUID;

@Entity
public class Report implements Serializable {

    @Id
    @Column(nullable = false, unique = true)
    private UUID reportId = UUID.randomUUID();


    @Column(nullable = false)
    private UUID reviewId;

    @Column(nullable = false)
    @Size(max = 2048)
    private String text;

    @Column(nullable = false)
    private Long userId;


    public Report(UUID reportId, UUID reviewId, String text, Long userId) {
        this.reportId = reportId;
        this.reviewId = reviewId;
        setText(text);
        this.userId = userId;
    }

    public Report(UUID reviewId, String text, Long userId) {
        this.reviewId = reviewId;
        setText(text);
        this.userId = userId;
    }

    public Report() {

    }

    public UUID getReportId() {
        return reportId;
    }

    public UUID getReviewId() {
        return reviewId;
    }

    public void setReviewId(UUID reviewId) {
        if (reviewId == null || reviewId.toString().isEmpty()){
            throw new IllegalArgumentException("Review Id is a mandatory attribute of a Report");
        }
        this.reviewId = reviewId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        if (text.length()>2048){
            throw new IllegalArgumentException("Report Text Length is too big");
        }
        if (text.trim().length()==0){
            throw new IllegalArgumentException("Report Text cannot be white spaces or empty");
        }
        this.text = text;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        if (userId == null){
            throw new IllegalArgumentException("User Id is a mandatory attribute of a Report");
        }
        this.userId = userId;
    }
}

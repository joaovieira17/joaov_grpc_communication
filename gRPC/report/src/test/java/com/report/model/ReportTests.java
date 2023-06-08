package com.report.model;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ReportTests {

    @Test
    void ensureTextHasCorrectSize() {
        Report report = new Report();
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> report.setText("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
        assertEquals("Report Text Length is too big", exception.getMessage());
    }

    @Test
    void ensureReportTextIsNotWhiteSpaces(){
        Report report = new Report();
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->report.setText("          "));
        assertEquals("Report Text cannot be white spaces or empty", exception.getMessage());
    }

    @Test
    void ensureTextIsAccepted() {
        Report report = new Report();
        report.setText("Review de mau gosto");
        assertEquals("Review de mau gosto", report.getText());
    }

    @Test
    void ensureUserIsNotNull(){
        Report report = new Report();
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->report.setUserId(null));
        assertEquals("User Id is a mandatory attribute of a Report", exception.getMessage());
    }

    @Test
    void ensureUserIsAccepted(){
        Report report = new Report();
        report.setUserId(5L);
        assertEquals(5, report.getUserId());
    }

    @Test
    void ensureReviewIdIsNotNull(){
        Report report = new Report();
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->report.setReviewId(null));
        assertEquals("Review Id is a mandatory attribute of a Report", exception.getMessage());
    }

    @Test
    void ensureReviewIdIsNotBlackSpaces(){
        Report report = new Report();
        assertThrows(IllegalArgumentException.class, () ->report.setReviewId(UUID.fromString("   ")));
    }

    @Test
    void ensureReviewIdIsUUID(){
        Report report = new Report();
        assertThrows(IllegalArgumentException.class, () ->report.setReviewId(UUID.fromString("afshf9")));
    }

    @Test
    void ensureReviewIdIsAccepted(){
        Report report = new Report();
        report.setReviewId(UUID.fromString("d728f1ce-c747-11ed-afa1-0242ac120002"));
        assertEquals(UUID.fromString("d728f1ce-c747-11ed-afa1-0242ac120002"), report.getReviewId());
    }


    @Test
    void ensureEverythingIsCorrectAndInPlace(){
        Report report = new Report(UUID.fromString("d728f1ce-c747-11ed-afa1-0242ac120001"),UUID.fromString("d728f1ce-c747-11ed-afa1-0242ac120002"),"Mau gosto",5L);
        assertEquals(UUID.fromString("d728f1ce-c747-11ed-afa1-0242ac120001"),report.getReportId());
        assertEquals(UUID.fromString("d728f1ce-c747-11ed-afa1-0242ac120002"),report.getReviewId());
        assertEquals("Mau gosto", report.getText());
        assertEquals(5,report.getUserId());
    }

    @Test
    void ensureUUIDisGenerated(){
        Report report = new Report(UUID.fromString("d728f1ce-c747-11ed-afa1-0242ac120002"),"Mau gosto",5L);
        assertNotEquals("",report.getReportId().toString());
        assertEquals(UUID.fromString("d728f1ce-c747-11ed-afa1-0242ac120002"),report.getReviewId());
        assertEquals("Mau gosto", report.getText());
        assertEquals(5,report.getUserId());
    }


}

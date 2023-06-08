package com.reservation.model;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ReservationTests {

    @Test
    void ensureCreationDateIsNotNull(){
        Reservation reservation = new Reservation();
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->reservation.setCreationDate(null));
        assertEquals("creation Date is a mandatory attribute of a Reservation", exception.getMessage());
    }

    @Test
    void ensureCreationDateIsAccepted(){
        Reservation reservation = new Reservation();
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        reservation.setCreationDate(date);

        assertEquals(reservation.getCreationDate(),date);
    }

    @Test
    void ensurePickupDateIsNotNull(){
        Reservation reservation = new Reservation();
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->reservation.setPickupDate(null));
        assertEquals("Pickup Date is a mandatory attribute of a Reservation", exception.getMessage());
    }

    @Test
    void ensurePickupDateIsNotBeforeCreationDate() throws ParseException {
        Reservation reservation = new Reservation();
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        reservation.setCreationDate(date);

        String pickupDateString = "2022-06-03T00:00:00.000+00:00";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Date pickupDate = dateFormat.parse(pickupDateString);

        Throwable exception = assertThrows(IllegalArgumentException.class, () ->reservation.setPickupDate(pickupDate));
        assertEquals("Pickup date cannot be before creation date", exception.getMessage());
    }


    @Test
    void ensurePickupDateIsAccepted() throws ParseException {
        Reservation reservation = new Reservation();
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        reservation.setCreationDate(date);

        String pickupDateString = "2024-06-03T00:00:00.000+00:00";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Date pickupDate = dateFormat.parse(pickupDateString);
        reservation.setPickupDate(pickupDate);
        assertEquals("Mon Jun 03 01:00:00 WEST 2024", reservation.getPickupDate().toString());
    }

    @Test
    void ensureUserIsNotNull(){
        Reservation reservation = new Reservation();
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->reservation.setUserId(null));
        assertEquals("User Id is a mandatory attribute of a Reservation", exception.getMessage());
    }

    @Test
    void ensureUserIsAccepted(){
        Reservation reservation = new Reservation();
        reservation.setUserId(5L);
        assertEquals(5, reservation.getUserId());
    }

    @Test
    void ensureStatusIsNotNull(){
        Reservation reservation = new Reservation();
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->reservation.setStatus(null));
        assertEquals("Status is a mandatory attribute of a Reservation", exception.getMessage());
    }

    @Test
    void ensureStatusIsAccepted(){
        Reservation reservation = new Reservation();
        reservation.setStatus(Reservation.ReservationStatus.ACTIVE);
        assertEquals("ACTIVE", reservation.getStatus().toString());
    }



    @Test
    void ensureListOfItemsIsNotEmpty(){
        Reservation reservation = new Reservation();

        List<ReservationItem> itemList = new ArrayList<>();

        Throwable exception = assertThrows(IllegalArgumentException.class, () ->reservation.setItems(itemList));
        assertEquals("You have to choose at least one item", exception.getMessage());
    }

    @Test
    void ensureListOfItemsIsNotAccepted(){
        ReservationItem item = new ReservationItem();
        assertThrows(IllegalArgumentException.class, () ->item.setSandwichId(UUID.fromString("   ")));
    }

    @Test
    void ensureListOfItemsIsNotAccepted2(){
        ReservationItem item = new ReservationItem();
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->item.setQuantity(0));
        assertEquals("Quantity should be a positive integer", exception.getMessage());
    }

    @Test
    void ensureListOfItemsIsAccepted(){
        Reservation reservation = new Reservation();

        ReservationItem item = new ReservationItem();
        item.setSandwichId(UUID.fromString("d728f1ce-c747-11ed-afa1-0242ac120002"));
        item.setQuantity(3);
        List<ReservationItem> itemList = new ArrayList<>();
        itemList.add(item);

        reservation.setItems(itemList);
        assertEquals(itemList, reservation.getItems());
    }


    @Test
    void ensureEverythingIsCorrectAndInPlace() throws ParseException {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);

        String pickupDateString = "2024-06-03T00:00:00.000+00:00";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Date pickupDate = dateFormat.parse(pickupDateString);

        ReservationItem item = new ReservationItem();
        item.setSandwichId(UUID.fromString("d728f1ce-c747-11ed-afa1-0242ac120002"));
        item.setQuantity(3);
        List<ReservationItem> itemList = new ArrayList<>();
        itemList.add(item);


        Reservation reservation = new Reservation(UUID.fromString("d728f1ce-c747-11ed-afa1-0242ac120001"),date,pickupDate,5L, Reservation.ReservationStatus.ACTIVE,itemList);
        assertEquals(UUID.fromString("d728f1ce-c747-11ed-afa1-0242ac120001"),reservation.getReservationId());
        assertEquals(date,reservation.getCreationDate());
        assertEquals("Mon Jun 03 01:00:00 WEST 2024", reservation.getPickupDate().toString());
        assertEquals(5,reservation.getUserId());
        assertEquals(UUID.fromString("d728f1ce-c747-11ed-afa1-0242ac120002"),reservation.getItems().get(0).getSandwichId());
        assertEquals(3, reservation.getItems().get(0).getQuantity());
    }

    @Test
    void ensureUUIDisGenerated() throws ParseException {
        String pickupDateString = "2024-06-03T00:00:00.000+00:00";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Date pickupDate = dateFormat.parse(pickupDateString);

        ReservationItem item = new ReservationItem();
        item.setSandwichId(UUID.fromString("d728f1ce-c747-11ed-afa1-0242ac120002"));
        item.setQuantity(3);
        List<ReservationItem> itemList = new ArrayList<>();
        itemList.add(item);

        Reservation reservation = new Reservation(pickupDate,5L,itemList);
        assertNotEquals("",reservation.getReservationId().toString());

    }

}

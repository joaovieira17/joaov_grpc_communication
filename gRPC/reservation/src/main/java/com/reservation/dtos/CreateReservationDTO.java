package com.reservation.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.reservation.model.ReservationItem;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class CreateReservationDTO {

    Date pickupDate;

    List<ReservationItem> itemList;

    public CreateReservationDTO(Date pickupDate, List<ReservationItem> itemList) {
        this.pickupDate = pickupDate;
        this.itemList = itemList;
    }

    public Date getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(Date pickupDate) {
        this.pickupDate = pickupDate;
    }

    public List<ReservationItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<ReservationItem> itemList) {
        this.itemList = itemList;
    }
}

package com.reservation.model;

import com.reservation.dtos.CreateReservationDTO;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
public class Reservation implements Serializable {

    public enum ReservationStatus {
        ACTIVE,
        INACTIVE,
        DELIVERED,
        NOT_DELIVERED,
        CANCELED
    }

    @Id
    @NotNull
    @NotBlank
    @Column(nullable = false, unique = true)
    private UUID reservationId = UUID.randomUUID();

    @Column(nullable = false)
    private Date creationDate;

    @Column(nullable = false)
    private Date pickupDate;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private ReservationStatus status;

    @Column(nullable = false)
    @ElementCollection
    private List<ReservationItem> items;


    @PrePersist
    public void prePersist() {
        if (pickupDate != null && pickupDate.before(creationDate)) {
            throw new IllegalArgumentException("Pickup date cannot be before creation date");
        }
        status = calculateStatus();
    }

    private ReservationStatus calculateStatus() {
        long millis = System.currentTimeMillis();
        Date now = new Date(millis);

        Calendar currentCalendar=calendarInstance(now);
        Calendar pickupCalendar= calendarInstance(pickupDate);

        if (currentCalendar.after(pickupCalendar)) {
            return ReservationStatus.INACTIVE;
        } else {
            return ReservationStatus.ACTIVE;
        }
    }

    private Calendar calendarInstance(Date now) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

    public Reservation(UUID reservationId, Date creationDate, Date pickupDate, Long userId, ReservationStatus status, List<ReservationItem> items) {
        this.reservationId = reservationId;
        setCreationDate(creationDate);
        setPickupDate(pickupDate);
        setUserId(userId);
        setStatus(status);
        setItems(items);
    }

    public Reservation(Date pickupDate, Long userId, List<ReservationItem> items) {
        long millis = System.currentTimeMillis();
        this.creationDate = new Date(millis);
        setPickupDate(pickupDate);
        setUserId(userId);
        this.status=ReservationStatus.ACTIVE;
        setItems(items);
    }

    public Reservation() {
    }

    public UUID getReservationId() {
        return reservationId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        if(creationDate==null){
            throw new IllegalArgumentException("creation Date is a mandatory attribute of a Reservation");
        }
        this.creationDate = creationDate;
    }

    public Date getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(Date pickupDate) {
        if(pickupDate==null){
            throw new IllegalArgumentException("Pickup Date is a mandatory attribute of a Reservation");
        }
        long millis = System.currentTimeMillis();
        Date atual = new Date(millis);
        if (pickupDate.before(atual)) {
            throw new IllegalArgumentException("Pickup date cannot be before creation date");
        }

        this.pickupDate = pickupDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        if(userId==null){
            throw new IllegalArgumentException("User Id is a mandatory attribute of a Reservation");
        }
        this.userId = userId;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        if(status==null){
            throw new IllegalArgumentException("Status is a mandatory attribute of a Reservation");
        }
        this.status = status;
    }

    public List<ReservationItem> getItems() {
        return items;
    }

    public void setItems(List<ReservationItem> items) {
        if(items.isEmpty()){
            throw new IllegalArgumentException("You have to choose at least one item");
        }
        this.items = items;
    }

}

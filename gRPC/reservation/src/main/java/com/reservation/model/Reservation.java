package com.reservation.model;

import com.reservation.dtos.CreateReservationDTO;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
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

    @Enumerated(EnumType.STRING)
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
        if (now.after(pickupDate)) {
            return ReservationStatus.INACTIVE;
        } else {
            return ReservationStatus.ACTIVE;
        }
    }

    public Reservation(UUID reservationId, Date creationDate, Date pickupDate, Long userId, ReservationStatus status, List<ReservationItem> items) {
        this.reservationId = reservationId;
        this.creationDate = creationDate;
        this.pickupDate = pickupDate;
        this.userId = userId;
        this.status = status;
        this.items = items;
    }

    public Reservation(Date pickupDate, Long userId, List<ReservationItem> items) {

        this.pickupDate = pickupDate;
        this.userId = userId;
        this.items = items;
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
        this.creationDate = creationDate;
    }

    public Date getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(Date pickupDate) {
        this.pickupDate = pickupDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public List<ReservationItem> getItems() {
        return items;
    }

    public void setItems(List<ReservationItem> items) {
        this.items = items;
    }

}

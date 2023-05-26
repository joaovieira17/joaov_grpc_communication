package com.reservation.repositories;

import com.reservation.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ReservationRepository extends JpaRepository<Reservation, UUID> {

    @Query("SELECT f FROM Reservation f WHERE f.reservationId= :reservationId")
    Reservation getByReservationId(@Param("reservationId") UUID reservationId);

    @Query("SELECT  f FROM Reservation f")
    List<Reservation> getListOfReservations();

    @Query("SELECT r FROM Reservation r WHERE r.status ='NON_ACTIVE'")
    List<Reservation> getAllNonActiveReservations();

    @Query("SELECT r FROM Reservation r WHERE r.status ='NON_ACTIVE' ORDER BY r.pickupDate asc")
    List<Reservation> getAllNonActiveReservationsOrderedByPickUpDate();

    @Query("SELECT r FROM Reservation r WHERE r.userId = :userId")
    List<Reservation> getAllMyReservations(@Param("userId") Long userId);



}

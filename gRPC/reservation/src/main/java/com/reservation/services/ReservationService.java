package com.reservation.services;

import com.reservation.dtos.CreateReservationDTO;
import com.reservation.model.Reservation;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface ReservationService {

    //MOD
    Reservation getByReservationId(UUID reservationId);
    //MOD
    List<Reservation> getListOfReservations();

    //MOD
    List<Reservation> getAllNonActiveReservations();

    //MOD
    List<Reservation> getNonActiveReservationsOrderedByPickUpDate();

    //MOD
    String changeDeliveredStatus(UUID reservationId, boolean isDelivered);

    //REGISTERED_USER
    List<Reservation> getAllMyReservations();

    //REGISTERED_USER
    Reservation createReservation(CreateReservationDTO createReservationDTO) throws IOException, InterruptedException;

    //REGISTERED_USER
    String cancelReservation(UUID reservationId);

    //MOD
    String deleteReservation(UUID reservationId);


}

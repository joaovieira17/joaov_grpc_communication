package com.reservation.services;

import com.reservation.dtos.CreateReservationDTO;
import com.reservation.model.Reservation;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface ReservationService {

    //EMPLOYEE
    Reservation getByReservationId(UUID reservationId);

    //EMPLOYEE
    List<Reservation> getListOfReservations();

    //EMPLOYEE
    List<Reservation> getAllNonActiveReservations();

    //EMPLOYEE
    List<Reservation> getNonActiveReservationsOrderedByPickUpDate();

    //EMPLOYEE
    String changeDeliveredStatus(UUID reservationId, boolean isDelivered);

    //REGISTERED_USER
    List<Reservation> getAllMyReservations();

    //REGISTERED_USER
    Reservation getMySpecificReservation(UUID reservationId);

    //REGISTERED_USER
    Reservation createReservation(CreateReservationDTO createReservationDTO) throws IOException, InterruptedException;

    //REGISTERED_USER
    String cancelReservation(UUID reservationId);

    //EMPLOYEE
    String deleteReservation(UUID reservationId);




}

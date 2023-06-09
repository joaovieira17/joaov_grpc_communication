package com.reservation.controllers;

import com.reservation.dtos.CreateReservationDTO;
import com.reservation.model.Reservation;
import com.reservation.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private ReservationService service;

    @GetMapping("/list")
    public Iterable<Reservation> getListOfReservations() {
        return service.getListOfReservations();
    }

    @GetMapping("/{reservationId}")
    public ResponseEntity<Reservation> getByReservationId(@PathVariable("reservationId") final UUID reservationId) {
        final Reservation reservation = service.getByReservationId(reservationId);
        return ResponseEntity.ok().body(reservation);
    }

    @GetMapping("/listOfNonActive")
    public Iterable<Reservation> getAllNonActiveReservations() {
        return service.getAllNonActiveReservations();
    }

    @GetMapping("/listOfNonActiveOrdered")
    public Iterable<Reservation> getNonActiveReservationsOrderedByPickUpDate() {
        return service.getNonActiveReservationsOrderedByPickUpDate();
    }

    @PutMapping(value = "/{reservationId}/changeStatus/{isDelivered}")
    public ResponseEntity<String> changeDeliveredStatus(@PathVariable("reservationId") final UUID reservationId, @PathVariable ("isDelivered") final boolean isDelivered){
        String event = service.changeDeliveredStatus(reservationId,isDelivered);
        return ResponseEntity.ok(event);
    }

    @GetMapping("/allMyReservations")
    public Iterable<Reservation> getAllMyReservations() {
        return service.getAllMyReservations();
    }

    @GetMapping("/myReserv/{reservationId}")
    public ResponseEntity<Reservation> getMyReservationById(@PathVariable("reservationId") final UUID reservationId) {
        final Reservation reservation = service.getMySpecificReservation(reservationId);
        return ResponseEntity.ok().body(reservation);
    }

    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Reservation> createReservation(@Valid @RequestBody final CreateReservationDTO createReservationDTO) throws IOException, InterruptedException {
        final Reservation reservation = service.createReservation(createReservationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(reservation);
    }

    @PutMapping(value = "/{reservationId}/cancelReservation")
    public ResponseEntity<String> changeDeliveredStatus(@PathVariable("reservationId") final UUID reservationId){
        String event = service.cancelReservation(reservationId);
        return ResponseEntity.ok(event);
    }

    @DeleteMapping(value = "/delete/{reservationId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteReservation(@PathVariable("reservationId")final UUID reservationId){
        return ResponseEntity.ok(service.deleteReservation(reservationId));
    }

}

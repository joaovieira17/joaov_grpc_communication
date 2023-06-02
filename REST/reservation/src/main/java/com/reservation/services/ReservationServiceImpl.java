package com.reservation.services;

import com.reservation.dtos.CreateReservationDTO;
import com.reservation.model.Reservation;
import com.reservation.model.ReservationItem;
import com.reservation.repositories.ReservationRepository;
import com.reservation.repositories.UserRepository;
import com.reservation.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReservationServiceImpl implements ReservationService{

    @Autowired
    private ReservationRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtils jwtUtils;

    private HttpRequestHelper helper=new HttpRequestHelper();

    @Override
    public Reservation getByReservationId(UUID reservationId) {
        Optional<Reservation> reservationOptional = Optional.ofNullable(repository.getByReservationId(reservationId));
        if (reservationOptional.isPresent())
            return reservationOptional.get();
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Reservation Not Found");
    }

    @Override
    public List<Reservation> getListOfReservations() {
        return repository.getListOfReservations();
    }

    @Override
    public List<Reservation> getAllNonActiveReservations() {
        return repository.getAllNonActiveReservations();
    }

    @Override
    public List<Reservation> getNonActiveReservationsOrderedByPickUpDate() {
        return repository.getAllNonActiveReservationsOrderedByPickUpDate();
    }

    @Override
    public String changeDeliveredStatus(UUID reservationId, boolean isDelivered) {
        Optional<Reservation> reservationOptional = Optional.ofNullable(repository.getByReservationId(reservationId));
        if(reservationOptional.isPresent()) {
            if (isDelivered) {
                reservationOptional.get().setStatus(Reservation.ReservationStatus.DELIVERED);
            } else {
                reservationOptional.get().setStatus(Reservation.ReservationStatus.NOT_DELIVERED);
            }
            repository.save(reservationOptional.get());

            return "Reservation status modified";
        }
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Reservation Not Found");
    }

    @Override
    public List<Reservation> getAllMyReservations() {
        Long userId = Long.valueOf(jwtUtils.getUserFromJwtToken(jwtUtils.getJwt()));
        return repository.getAllMyReservations(userId);
    }

    @Override
    public Reservation createReservation(CreateReservationDTO createReservationDTO) throws IOException, InterruptedException {
        long millis = System.currentTimeMillis();
        Date atual = new Date(millis);
        Long userId = Long.valueOf(jwtUtils.getUserFromJwtToken(jwtUtils.getJwt()));

        if (createReservationDTO.getPickupDate() != null && createReservationDTO.getPickupDate().before(atual)) {
            throw new IllegalArgumentException("Pickup date cannot be before creation date");
        }

        List<ReservationItem> reservationItemList = createReservationDTO.getItemList();

        for(int i=0; i<reservationItemList.size();i++){
            boolean sandwichExistence = helper.doesSandwichExist(createReservationDTO.getItemList().get(i).getSandwichPublicKey());
            if(!sandwichExistence){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"That sandwich does not exist: "+createReservationDTO.getItemList().get(i).getSandwichPublicKey());
            }
        }

        Reservation reservation = new Reservation();

        reservation.setPickupDate(createReservationDTO.getPickupDate());
        reservation.setItems(createReservationDTO.getItemList());
        reservation.setUserId(userId);


        reservation.setCreationDate(atual);
        reservation.setStatus(Reservation.ReservationStatus.STILL_ACTIVE);
        repository.save(reservation);
        return reservation;
    }

    @Override
    public String cancelReservation(UUID reservationId) {
        Optional<Reservation> reservationOptional = Optional.ofNullable(repository.getByReservationId(reservationId));
        if (reservationOptional.isPresent()) {
            reservationOptional.get().setStatus(Reservation.ReservationStatus.CANCELED);
            repository.save(reservationOptional.get());
            return "Reservation Canceled";
        }
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Reservation Not Found");
    }

    @Override
    public String deleteReservation(UUID reservationId) {
        Optional<Reservation> reservationOptional = Optional.ofNullable(repository.getByReservationId(reservationId));
        if (reservationOptional.isPresent()) {
            repository.delete(reservationOptional.get());
            return "Reservation Deleted";
        }
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Reservation Not Found");
    }
}
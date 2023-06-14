package com.reservation.services;

import com.joao.sandwich.SandwichResponse;
import com.reservation.dtos.CreateReservationDTO;
import com.reservation.grpcService.SandwichGrpcService;
import com.reservation.model.Reservation;
import com.reservation.model.ReservationItem;
import com.reservation.repositories.ReservationRepository;
import com.reservation.security.JwtUtils;
import com.reservation.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReservationServiceImpl implements ReservationService{

    @Autowired
    private ReservationRepository repository;

    @Autowired
    private JwtUtils jwtUtils;

    private final SandwichGrpcService sandwichGrpcService;

    public ReservationServiceImpl(SandwichGrpcService sandwichGrpcService) {
        this.sandwichGrpcService = sandwichGrpcService;
    }

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
    public Reservation getMySpecificReservation(UUID reservationId) {
        Long userId = Long.valueOf(jwtUtils.getUserFromJwtToken(jwtUtils.getJwt()));
        Optional<Reservation> reservationOptional = Optional.ofNullable(repository.getMyReservationById(userId,reservationId));
        if (reservationOptional.isPresent())
            return reservationOptional.get();
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Reservation Not Found");
    }

    @Override
    public Reservation createReservation(CreateReservationDTO createReservationDTO)  {
        long millis = System.currentTimeMillis();
        Date atual = new Date(millis);
        Long userId = Long.valueOf(jwtUtils.getUserFromJwtToken(jwtUtils.getJwt()));

        if (createReservationDTO.getPickupDate() != null && createReservationDTO.getPickupDate().before(atual)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Pickup date cannot be before creation date");
        }

        List<ReservationItem> reservationItemList = createReservationDTO.getItemList();
        Utils.verifyDuplicatedItems(reservationItemList);
        for(int i=0; i<reservationItemList.size();i++){
            SandwichResponse response = sandwichGrpcService.getSandwich(createReservationDTO.getItemList().get(i).getSandwichId());
            if(response.getCode()==404){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"That sandwich does not exist: "+createReservationDTO.getItemList().get(i).getSandwichId());
            }
        }

        Reservation reservation = new Reservation();

        reservation.setPickupDate(createReservationDTO.getPickupDate());
        reservation.setItems(createReservationDTO.getItemList());
        reservation.setUserId(userId);


        reservation.setCreationDate(atual);
        reservation.setStatus(Reservation.ReservationStatus.ACTIVE);
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

package com.yahya.justlife.controllers;


import com.sun.istack.NotNull;
import com.yahya.justlife.entities.Reservation;
import com.yahya.justlife.requests.ReservationCreateRequest;
import com.yahya.justlife.requests.ReservationUpdateRequest;
import com.yahya.justlife.services.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private ReservationService reservationService ;

    public ReservationController(ReservationService reservationService){
        this.reservationService = reservationService ;
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations(){
        return new ResponseEntity<>(reservationService.getAllReservations(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Reservation> createReservation(ReservationCreateRequest request){
        try{
            return new ResponseEntity<>(reservationService.createReservation(request),HttpStatus.OK);
        }catch(Exception e){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping()
    public ResponseEntity<Reservation> updateReservation(ReservationUpdateRequest request) {
        try {
            return new ResponseEntity<>(reservationService.updateReservation(request),HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping()
    public ResponseEntity<Long> deleteReservation(@NotNull Long id){
        try{
            reservationService.deleteReservation(id);
            return new ResponseEntity<>(id,HttpStatus.OK);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
        }

    }

}

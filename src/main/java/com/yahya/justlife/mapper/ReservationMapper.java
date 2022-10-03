package com.yahya.justlife.mapper;

import com.yahya.justlife.entities.CleanerProfessional;
import com.yahya.justlife.entities.Reservation;
import com.yahya.justlife.requests.BaseReservationRequest;
import com.yahya.justlife.requests.ReservationUpdateRequest;

import java.time.LocalTime;
import java.util.List;

public class ReservationMapper {

    public static Reservation createReservationEntityFromReservationRequest(BaseReservationRequest request, List<CleanerProfessional> cleanerProfessionalList){
        boolean isUpdateRequest = request instanceof ReservationUpdateRequest;
        Reservation reservation = new Reservation();
        if(isUpdateRequest){
            reservation.setId(((ReservationUpdateRequest)request).getId());
        }
        reservation.setDate(request.getDate());
        reservation.setStartTime(LocalTime.of(request.getStartHour(),request.getStartMinute()));
        reservation.setEndTime(LocalTime.of(request.getStartHour()+request.getDuration(), request.getStartMinute()));
        reservation.setDuration(request.getDuration());
        reservation.setCleanerProfessionalsOfReservation(cleanerProfessionalList);
        return reservation ;
    }


}

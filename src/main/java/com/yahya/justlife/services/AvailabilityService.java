package com.yahya.justlife.services;

import com.yahya.justlife.entities.CleanerProfessional;
import com.yahya.justlife.entities.Reservation;
import com.yahya.justlife.responses.AvailabilityResponse;
import com.yahya.justlife.responses.AvailableTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AvailabilityService {

    @Autowired
    private CleanerProfessionalService cleanerProfessionalService ;

    public List<AvailabilityResponse> availabilityCheckWithDate(Date date){

        List<AvailabilityResponse> response = new ArrayList<>();
        List<CleanerProfessional> cleanerProfessionalList = cleanerProfessionalService.getAllCleanerProfessionals();

        for(CleanerProfessional cleanerProfessional : cleanerProfessionalList){

            AvailabilityResponse availabilityResponse = new AvailabilityResponse() ;
            availabilityResponse.setCleanerProfessional(cleanerProfessional);
            List<Reservation> reservationsAtTheSameDate = new ArrayList<>();

            for(Reservation reservation : cleanerProfessional.getReservationList()){
                if(reservation.getDate().equals(date)){
                    reservationsAtTheSameDate.add(reservation);
                }
            }
            prepareAvailibilityTime(availabilityResponse,reservationsAtTheSameDate);
            response.add(availabilityResponse);
        }
        return response ;
    }

    private void prepareAvailibilityTime(AvailabilityResponse availabilityResponse, List<Reservation> reservationsAtTheSameDate) {
        AvailableTime availableTime = new AvailableTime();
        availableTime.setStartTime(LocalTime.of(8,00));

        for(Reservation reservation : reservationsAtTheSameDate){

            LocalTime reservationStartTime = reservation.getStartTime().minusMinutes(30);
            availableTime.setEndTime(reservationStartTime);
            if(!availableTime.getStartTime().isAfter(availableTime.getEndTime()))
                availabilityResponse.getAvailableTimeList().add(availableTime);

            availableTime = new AvailableTime();
            LocalTime reservationEndTime = reservation.getEndTime().plusMinutes(30);
            availableTime.setStartTime(reservationEndTime);

        }
        availableTime.setEndTime(LocalTime.of(22,0));
        if(!availableTime.getStartTime().isAfter(availableTime.getEndTime()))
            availabilityResponse.getAvailableTimeList().add(availableTime);

    }


    public List<CleanerProfessional> availableCleanerProfessionalsWithDateAndTime(Date date, LocalTime startTime,LocalTime endTime) {
        List<CleanerProfessional> response = new ArrayList<>();
        List<CleanerProfessional> cleanerProfessionalList = cleanerProfessionalService.getAllCleanerProfessionals();

        for(CleanerProfessional cleanerProfessional : cleanerProfessionalList){
            boolean available = true ;
            for(Reservation reservation : cleanerProfessional.getReservationList()){

                if(reservation.getDate().equals(date)){

                    LocalTime reservationStartTime = reservation.getStartTime() ;
                    LocalTime reservationEndTime = reservation.getEndTime();

                    if( startTime.isAfter(reservationStartTime) && startTime.isBefore(reservationEndTime) ||
                        endTime.isAfter(reservationStartTime) && endTime.isBefore(reservationEndTime)  ){
                        available = false;
                        break;
                    }

                }
            }
            if(available)
                response.add(cleanerProfessional);
        }
        return response ;
    }
}

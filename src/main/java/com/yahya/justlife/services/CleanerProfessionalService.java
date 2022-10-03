package com.yahya.justlife.services;


import com.yahya.justlife.entities.CleanerProfessional;
import com.yahya.justlife.entities.Reservation;
import com.yahya.justlife.repositories.CleanerProfessionalRepository;
import com.yahya.justlife.requests.BaseReservationRequest;
import com.yahya.justlife.requests.ReservationCreateRequest;
import com.yahya.justlife.requests.ReservationUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.List;

@Service
public class CleanerProfessionalService {
    @Autowired
    private CleanerProfessionalRepository cleanerProfessionalRepository ;

    public List<CleanerProfessional> getAllCleanerProfessionals() {
        return cleanerProfessionalRepository.findAll();
    }

    public List<CleanerProfessional> findAllById(List<Long> cleanerProfessionalsIds) {
        return cleanerProfessionalRepository.findAllById(cleanerProfessionalsIds);
    }

    public void areCleanerProfessionalsExists(BaseReservationRequest request) throws Exception{
        if(request.getCleanerProfessionalsIds().size() <= 0)
            throw new Exception("ClanerProfessional Ids count can not zero or below");
        if(!(request.getCleanerProfessionalsIds().size() == cleanerProfessionalRepository.countByIdIn(request.getCleanerProfessionalsIds())))
            throw new Exception("CleanerProfessional's ids are not valid");
    }

    public void isStartAndEndTimeValid(BaseReservationRequest request) throws Exception {
        if( request.getStartHour() < 8)
            throw new Exception("StartTime is not valid");

        if(request.getDuration() == 2){
            if(!(request.getStartHour() < 20 || (request.getStartHour() == 20 && request.getStartMinute() == 0)))
                throw new Exception("EndTime is not valid");
        }else{
            if(!(request.getStartHour() < 18 || (request.getStartHour() == 18 && request.getStartMinute()== 0)))
                throw new Exception("EndTime is not valid");
        }

    }

    public void isWorkingDayValid(BaseReservationRequest request) throws Exception {
        String workingDay = new SimpleDateFormat("EEEE").format(request.getDate()) ;
        if(workingDay.equalsIgnoreCase("Cuma")) {
            throw new Exception("Friday is not working day");
        }
    }

    public void areCleanerProfessionalsAssignedTheSameCar(List<CleanerProfessional> cleanerProfessionalList) throws Exception {
        Long carId = cleanerProfessionalList.get(0).getCar().getId();

        for(int i = 1 ; i < cleanerProfessionalList.size() ; i++){
            if(!(cleanerProfessionalList.get(i).getCar().getId() == carId)){
                throw new Exception("Cleaner Professionals are not in the same car");
            }
        }
    }

    public void areCleanerProfessionalsCanBreak(List<CleanerProfessional> cleanerProfessionalList, BaseReservationRequest request) throws Exception {
        boolean isUpdateRequest = request instanceof ReservationUpdateRequest ;

        for(CleanerProfessional cleanerProfessional : cleanerProfessionalList){

            for(Reservation reservation : cleanerProfessional.getReservationList()){
                if(isUpdateRequest && ((ReservationUpdateRequest)request).getId() == reservation.getId()){
                    continue;
                }
                if(request.getDate().equals(reservation.getDate())) {
                    if(!isValidTimeDifference(reservation,request)){
                        throw new Exception("The reservation is not valid for some cleaner professionals");
                    }
                }
            }
        }
    }

    public boolean isValidTimeDifference(Reservation reservation, BaseReservationRequest request) {
        LocalTime cleanerProfessionalReservationStartTime = LocalTime.of(reservation.getStartTime().getHour(),reservation.getStartTime().getMinute());
        LocalTime cleanerProfessionalReservationEndTime = LocalTime.of(reservation.getEndTime().getHour(),reservation.getEndTime().getMinute()) ;

        cleanerProfessionalReservationStartTime = cleanerProfessionalReservationStartTime.minusMinutes(30);
        cleanerProfessionalReservationEndTime = cleanerProfessionalReservationEndTime.plusMinutes(30);

        LocalTime newReservationStartTime = LocalTime.of(request.getStartHour(),request.getStartMinute());
        LocalTime newReservationEndTime = LocalTime.of(request.getStartHour()+request.getDuration(),request.getStartMinute());

        return newReservationEndTime.isBefore(cleanerProfessionalReservationStartTime) ||
                newReservationStartTime.isAfter(cleanerProfessionalReservationEndTime);

    }


}

package com.yahya.justlife.services;

import com.yahya.justlife.entities.CleanerProfessional;
import com.yahya.justlife.entities.Reservation;
import com.yahya.justlife.mapper.ReservationMapper;
import com.yahya.justlife.repositories.ReservationRepository;
import com.yahya.justlife.requests.BaseReservationRequest;
import com.yahya.justlife.requests.ReservationCreateRequest;
import com.yahya.justlife.requests.ReservationUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository ;

    @Autowired
    private CleanerProfessionalService cleanerProfessionalService;

    public List<Reservation> getAllReservations(){
        return reservationRepository.findAll();
    }

    public Reservation createReservation(BaseReservationRequest request) throws Exception {
        return reservationBusinessRulesControlAndSave(request);
    }

    public Reservation updateReservation(BaseReservationRequest request) throws Exception{
        return reservationBusinessRulesControlAndSave(request);
    }

    public Reservation reservationBusinessRulesControlAndSave(BaseReservationRequest request) throws Exception{
        isDurationValid(request);
        cleanerProfessionalService.isWorkingDayValid(request);
        cleanerProfessionalService.isStartAndEndTimeValid(request);
        cleanerProfessionalService.areCleanerProfessionalsExists(request) ;

        List<CleanerProfessional> cleanerProfessionalList = cleanerProfessionalService.findAllById(request.getCleanerProfessionalsIds());

        cleanerProfessionalService.areCleanerProfessionalsAssignedTheSameCar(cleanerProfessionalList) ;
        cleanerProfessionalService.areCleanerProfessionalsCanBreak(cleanerProfessionalList,request);
        return reservationRepository.save(ReservationMapper.createReservationEntityFromReservationRequest(request,cleanerProfessionalList));
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }
    public void isDurationValid(BaseReservationRequest request) throws Exception {
        if( !(request.getDuration() == 2 || request.getDuration() == 4) )
            throw new Exception("Duration is not valid");
    }


}

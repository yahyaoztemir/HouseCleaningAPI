package com.yahya.justlife.services;

import com.yahya.justlife.entities.CleanerProfessional;
import com.yahya.justlife.entities.Reservation;
import com.yahya.justlife.responses.AvailabilityResponse;
import com.yahya.justlife.responses.AvailableTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
class AvailabilityServiceTest {

    @InjectMocks
    private AvailabilityService availabilityService;

    @Mock
    private CleanerProfessionalService cleanerProfessionalService ;

    @Test
    void availabilityCheckWithDate() {

        Reservation reservation = new Reservation();

        reservation.setDate(Date.valueOf("2022-11-1"));
        reservation.setStartTime(LocalTime.of(15,0));
        reservation.setEndTime(LocalTime.of(17,0));

        CleanerProfessional cleanerProfessional = new CleanerProfessional();
        cleanerProfessional.setReservationList(new ArrayList<>());
        cleanerProfessional.getReservationList().add(reservation);

        List<CleanerProfessional> cleanerProfessionalList = new ArrayList<>();
        cleanerProfessionalList.add(cleanerProfessional);

        List<AvailableTime> timeList = new ArrayList<>();
        AvailableTime first = new AvailableTime();
        first.setStartTime(LocalTime.of(8,0));
        first.setEndTime(LocalTime.of(14,30));
        AvailableTime second = new AvailableTime();
        second.setStartTime(LocalTime.of(17,30));
        second.setEndTime(LocalTime.of(22,0));
        timeList.add(first);
        timeList.add(second);

        List<AvailabilityResponse> availabilityResponseList = new ArrayList<>();
        AvailabilityResponse availabilityResponse = new AvailabilityResponse();
        availabilityResponse.setCleanerProfessional(cleanerProfessional);
        availabilityResponse.setAvailableTimeList(timeList);
        availabilityResponseList.add(availabilityResponse);

        given(cleanerProfessionalService.getAllCleanerProfessionals()).willReturn(cleanerProfessionalList);

        List<AvailabilityResponse> response = availabilityService.availabilityCheckWithDate(Date.valueOf("2022-11-1"));

        for(int i = 0 ; i < response.size() ; i++){
            for(int k = 0 ; k < response.get(i).getAvailableTimeList().size() ; k++){
                assertTrue(response.get(i).getAvailableTimeList().get(k).getStartTime().equals(availabilityResponseList.get(i).getAvailableTimeList().get(k).getStartTime()));
                assertTrue(response.get(i).getAvailableTimeList().get(k).getEndTime().equals(availabilityResponseList.get(i).getAvailableTimeList().get(k).getEndTime()));
            }
        }

    }


    @Test
    void cleanerProfessionalHasNoReservationAtTheGivenDateAndTime_thenShouldReturn() {

        CleanerProfessional cleanerProfessional = new CleanerProfessional();
        cleanerProfessional.setReservationList(new ArrayList<>());

        List<CleanerProfessional> cleanerProfessionalList = new ArrayList<>();
        cleanerProfessionalList.add(cleanerProfessional);

        given(cleanerProfessionalService.getAllCleanerProfessionals()).willReturn(cleanerProfessionalList);

        List<CleanerProfessional> response = availabilityService.availableCleanerProfessionalsWithDateAndTime(Date.valueOf("2022-11-5"),LocalTime.of(10,30),LocalTime.of(12,30)) ;

        assertEquals(response,cleanerProfessionalList);

    }

    @Test
    void cleanerProfessionalHasReservationAtTheGivenDateAndTime_thenShouldNotReturn() {

        CleanerProfessional cleanerProfessional = new CleanerProfessional();
        cleanerProfessional.setReservationList(new ArrayList<>());

        Reservation reservation = new Reservation();
        reservation.setDate(Date.valueOf("2022-11-5"));
        reservation.setStartTime(LocalTime.of(11,30));
        reservation.setEndTime(LocalTime.of(15,30));
        cleanerProfessional.getReservationList().add(reservation);

        List<CleanerProfessional> cleanerProfessionalList = new ArrayList<>();
        cleanerProfessionalList.add(cleanerProfessional);

        given(cleanerProfessionalService.getAllCleanerProfessionals()).willReturn(cleanerProfessionalList);

        List<CleanerProfessional> response = availabilityService.availableCleanerProfessionalsWithDateAndTime(Date.valueOf("2022-11-5"),LocalTime.of(10,30),LocalTime.of(12,30)) ;

        assertNotEquals(response,cleanerProfessionalList);

    }
}
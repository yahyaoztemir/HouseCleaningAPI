package com.yahya.justlife.services;

import com.yahya.justlife.entities.CleanerProfessional;
import com.yahya.justlife.entities.Reservation;
import com.yahya.justlife.repositories.ReservationRepository;
import com.yahya.justlife.requests.ReservationCreateRequest;
import com.yahya.justlife.requests.ReservationUpdateRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;
import java.time.LocalTime;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ReservationServiceTest {

    @InjectMocks
    private ReservationService reservationService ;

    @Mock
    private CleanerProfessionalService cleanerProfessionalService ;

    @Mock
    private ReservationRepository reservationRepository;

    @Test
    void getAllReservations() {
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(new Reservation());
        given(reservationRepository.findAll()).willReturn(reservations);

        List<Reservation> expected = reservationService.getAllReservations();

        assertEquals(expected, reservations);
    }

    @Test
    void createReservation() throws Exception {
        Reservation reservation = new Reservation();
        reservation.setDate(new Date(2022,11,2));
        reservation.setDuration(2);
        reservation.setStartTime(LocalTime.of(14,0));
        reservation.setEndTime(LocalTime.of(16,00));
        List<CleanerProfessional> cleanerProfessionalList = new ArrayList<>();
        CleanerProfessional cleanerProfessional = new CleanerProfessional();
        cleanerProfessional.setId(1L);
        CleanerProfessional cleanerProfessional2 = new CleanerProfessional();
        cleanerProfessional2.setId(2L);
        cleanerProfessionalList.add(cleanerProfessional);
        cleanerProfessionalList.add(cleanerProfessional2);
        reservation.setCleanerProfessionalsOfReservation(cleanerProfessionalList);

        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(2L);
        given(cleanerProfessionalService.findAllById(ids)).willReturn(cleanerProfessionalList);
        given(reservationRepository.save(ArgumentMatchers.any(Reservation.class))).willReturn(reservation);

        ReservationCreateRequest request = new ReservationCreateRequest();
        request.setDuration(2);
        request.setDate(new Date(2022-11-02));
        request.setStartHour(14);
        request.setStartMinute(0);
        request.setCleanerProfessionalsIds(ids);
        Reservation created = reservationService.createReservation(request);

        assertEquals(created,reservation);

    }

    @Test
    void updateReservation() throws Exception {
        Reservation reservation = new Reservation();
        reservation.setId(5L);
        reservation.setDate(new Date(2022,11,2));
        reservation.setDuration(2);
        reservation.setStartTime(LocalTime.of(14,0));
        reservation.setEndTime(LocalTime.of(16,00));
        List<CleanerProfessional> cleanerProfessionalList = new ArrayList<>();
        CleanerProfessional cleanerProfessional = new CleanerProfessional();
        cleanerProfessional.setId(1L);
        CleanerProfessional cleanerProfessional2 = new CleanerProfessional();
        cleanerProfessional2.setId(2L);
        cleanerProfessionalList.add(cleanerProfessional);
        cleanerProfessionalList.add(cleanerProfessional2);
        reservation.setCleanerProfessionalsOfReservation(cleanerProfessionalList);

        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(2L);
        given(cleanerProfessionalService.findAllById(ids)).willReturn(cleanerProfessionalList);
        given(reservationRepository.save(ArgumentMatchers.any(Reservation.class))).willReturn(reservation);

        ReservationUpdateRequest request = new ReservationUpdateRequest();
        request.setId(5L);
        request.setDuration(2);
        request.setDate(new Date(2022-11-02));
        request.setStartHour(14);
        request.setStartMinute(0);
        request.setCleanerProfessionalsIds(ids);
        Reservation created = reservationService.updateReservation(request);

        assertEquals(created,reservation);
    }

    @Test
    void durationNotValid_thenThrowsException() throws Exception{
        ReservationCreateRequest reservationCreateRequest = new ReservationCreateRequest();
        reservationCreateRequest.setDuration(6);
        assertThrows(Exception.class,() -> { reservationService.isDurationValid(reservationCreateRequest); });
    }

    @Test
    void durationValid_thenSuccess(){
        ReservationCreateRequest reservationCreateRequest = new ReservationCreateRequest();
        reservationCreateRequest.setDuration(4);
        assertDoesNotThrow(() -> { reservationService.isDurationValid(reservationCreateRequest); });
    }

}
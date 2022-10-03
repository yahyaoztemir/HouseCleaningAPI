package com.yahya.justlife.services;

import com.yahya.justlife.entities.Car;
import com.yahya.justlife.entities.CleanerProfessional;
import com.yahya.justlife.entities.Reservation;
import com.yahya.justlife.repositories.CleanerProfessionalRepository;
import com.yahya.justlife.requests.ReservationCreateRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
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
class CleanerProfessionalServiceTest {

    @InjectMocks
    private CleanerProfessionalService cleanerProfessionalService ;

    @Mock
    private CleanerProfessionalRepository cleanerProfessionalRepository ;

    @Test
    void getAllCleanerProfessionals() {
        List<CleanerProfessional> cleanerProfessionalList = new ArrayList<>();
        CleanerProfessional cp1 = new CleanerProfessional();
        cp1.setName("CP1");
        cp1.setRating(3.8);
        CleanerProfessional cp2 = new CleanerProfessional();
        cp2.setName("CP2");
        cp2.setRating(2.5);
        cleanerProfessionalList.add(cp1);
        cleanerProfessionalList.add(cp2);

        given(cleanerProfessionalRepository.findAll()).willReturn(cleanerProfessionalList);

        List<CleanerProfessional> response = cleanerProfessionalService.getAllCleanerProfessionals();

        assertEquals(response,cleanerProfessionalList);


    }

    @Test
    void findAllById() {
        List<CleanerProfessional> cleanerProfessionalList = new ArrayList<>();
        CleanerProfessional cp1 = new CleanerProfessional();
        cp1.setId(1L);
        CleanerProfessional cp2 = new CleanerProfessional();
        cp2.setId(2L);
        cleanerProfessionalList.add(cp1);
        cleanerProfessionalList.add(cp2);

        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(2L);

        given(cleanerProfessionalRepository.findAllById(ids)).willReturn(cleanerProfessionalList);

        List<CleanerProfessional> response = cleanerProfessionalService.findAllById(ids);

        assertEquals(response,cleanerProfessionalList);

    }

    @Test
    void cleanerProfessionalsIdsSizeZero_thenShouldThrowsException() {

        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(2L);
        ids.add(3L);

        ReservationCreateRequest reservationCreateRequest = new ReservationCreateRequest();
        reservationCreateRequest.setCleanerProfessionalsIds(new ArrayList<>());

        assertThrows(Exception.class, () -> {

            cleanerProfessionalService.areCleanerProfessionalsExists(reservationCreateRequest);

        } );
    }

    @Test
    void cleanerProfessionalsIdsSizeThreeButTwoCleanerProfessionalsHave_thenShouldThrowsException() {

        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(2L);
        ids.add(3L);

        ReservationCreateRequest reservationCreateRequest = new ReservationCreateRequest();
        reservationCreateRequest.setCleanerProfessionalsIds(ids);

        given(cleanerProfessionalRepository.countByIdIn(ArgumentMatchers.anyList())).willReturn(2);

        assertThrows(Exception.class, () -> {

            cleanerProfessionalService.areCleanerProfessionalsExists(reservationCreateRequest);

        } );
    }

    @Test
    void cleanerProfessionalsIdsSizeThreeAndThreeCleanerProfessionalsHave_thenShouldNotThrowsException() {

        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(2L);
        ids.add(3L);

        ReservationCreateRequest reservationCreateRequest = new ReservationCreateRequest();
        reservationCreateRequest.setCleanerProfessionalsIds(ids);

        given(cleanerProfessionalRepository.countByIdIn(ArgumentMatchers.anyList())).willReturn(3);

        assertDoesNotThrow(() -> {

            cleanerProfessionalService.areCleanerProfessionalsExists(reservationCreateRequest);

        } );
    }


    @Test
    void startTimeIsNotValid_thenShouldThrowsException() {
        ReservationCreateRequest createRequest = new ReservationCreateRequest();
        createRequest.setStartHour(7);
        assertThrows(Exception.class, () -> {
            cleanerProfessionalService.isStartAndEndTimeValid(createRequest);
        } );
    }

    @Test
    void startTimeIsValidForStartingButNotEnding_thenShouldThrowsException() {
        ReservationCreateRequest createRequest = new ReservationCreateRequest();
        createRequest.setDuration(4);
        createRequest.setStartHour(19);
        assertThrows(Exception.class, () -> {
            cleanerProfessionalService.isStartAndEndTimeValid(createRequest);
        } );
    }

    @Test
    void startTimeAndEndTimeIsValid_thenShouldNotThrowsException() {
        ReservationCreateRequest createRequest = new ReservationCreateRequest();
        createRequest.setDuration(4);
        createRequest.setStartHour(14);
        assertDoesNotThrow(() -> {
            cleanerProfessionalService.isStartAndEndTimeValid(createRequest);
        } );
    }

    @Test
    void workingDayIsValid_thenShouldNotThrowsException() {
        ReservationCreateRequest request = new ReservationCreateRequest();
        request.setDate(Date.valueOf("2022-10-12"));
        assertDoesNotThrow(()->{
            cleanerProfessionalService.isWorkingDayValid(request);
        });
    }

    @Test
    void workingDayIsNotValid_thenShouldThrowsException() {
        ReservationCreateRequest request = new ReservationCreateRequest();
        request.setDate(Date.valueOf("2022-10-14"));
        assertThrows(Exception.class , () -> {
            cleanerProfessionalService.isWorkingDayValid(request);
        });
    }

    @Test
    void cleanerProfessionalsAreAssignedTheSameCar_thenShouldNotThrowsException() {
        List<CleanerProfessional> cleanerProfessionalList = new ArrayList<>();
        Car car = new Car();
        car.setId(1L);
        CleanerProfessional cp1 = new CleanerProfessional();
        cp1.setCar(car);
        CleanerProfessional cp2 = new CleanerProfessional();
        cp2.setCar(car);
        cleanerProfessionalList.add(cp1);
        cleanerProfessionalList.add(cp2);

        assertDoesNotThrow(() -> {
            cleanerProfessionalService.areCleanerProfessionalsAssignedTheSameCar(cleanerProfessionalList);
        });

    }

    @Test
    void cleanerProfessionalsAreAssignedDifferentCar_thenShouldThrowsException() {
        List<CleanerProfessional> cleanerProfessionalList = new ArrayList<>();
        Car car1 = new Car();
        car1.setId(1L);
        Car car2 = new Car();
        car2.setId(2L);
        CleanerProfessional cp1 = new CleanerProfessional();
        cp1.setCar(car1);
        CleanerProfessional cp2 = new CleanerProfessional();
        cp2.setCar(car2);
        cleanerProfessionalList.add(cp1);
        cleanerProfessionalList.add(cp2);

        assertThrows(Exception.class , () -> {
            cleanerProfessionalService.areCleanerProfessionalsAssignedTheSameCar(cleanerProfessionalList);
        });

    }

    @Test
    void cleanerProfessionalsCanBreak_thenShouldNotThrowsException() {
        List<CleanerProfessional> cleanerProfessionalList = new ArrayList<>();
        CleanerProfessional cleanerProfessional = new CleanerProfessional();
        List<Reservation> reservationList = new ArrayList<>();
        Reservation reservation = new Reservation();
        reservation.setDate(Date.valueOf("2022-10-10"));
        reservationList.add(reservation);
        cleanerProfessional.setReservationList(reservationList);
        cleanerProfessionalList.add(cleanerProfessional);

        ReservationCreateRequest reservationCreateRequest = new ReservationCreateRequest();
        reservationCreateRequest.setDate(Date.valueOf("2022-10-3"));

        assertDoesNotThrow(()-> {
            cleanerProfessionalService.areCleanerProfessionalsCanBreak(cleanerProfessionalList,reservationCreateRequest);
        });
    }

    @Test
    void cleanerProfessionalsCanNotBreak_thenShouldThrowsException() {
        List<CleanerProfessional> cleanerProfessionalList = new ArrayList<>();
        CleanerProfessional cleanerProfessional = new CleanerProfessional();
        List<Reservation> reservationList = new ArrayList<>();
        Reservation reservation = new Reservation();
        reservation.setDate(Date.valueOf("2022-10-10"));
        reservation.setStartTime(LocalTime.of(10,30));
        reservation.setDuration(2);
        reservation.setEndTime(LocalTime.of(12,30));
        reservationList.add(reservation);
        cleanerProfessional.setReservationList(reservationList);
        cleanerProfessionalList.add(cleanerProfessional);

        ReservationCreateRequest reservationCreateRequest = new ReservationCreateRequest();
        reservationCreateRequest.setDate(Date.valueOf("2022-10-10"));
        reservationCreateRequest.setDuration(2);
        reservationCreateRequest.setStartHour(12);
        reservationCreateRequest.setStartMinute(45);
        assertThrows(Exception.class , ()-> {
            cleanerProfessionalService.areCleanerProfessionalsCanBreak(cleanerProfessionalList,reservationCreateRequest);
        });

    }
}
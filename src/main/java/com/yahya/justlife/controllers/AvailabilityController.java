package com.yahya.justlife.controllers;


import com.sun.istack.NotNull;
import com.yahya.justlife.entities.CleanerProfessional;
import com.yahya.justlife.responses.AvailabilityResponse;
import com.yahya.justlife.services.AvailabilityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.time.LocalTime;
import java.util.List;

@RestController("/availabilitycheck")
public class AvailabilityController {

    private AvailabilityService availabilityService ;

    public AvailabilityController(AvailabilityService availabilityService) {
        this.availabilityService = availabilityService;
    }

    @GetMapping("/availabilityCheckWithDate")
    public ResponseEntity<List<AvailabilityResponse>> availabilityCheckWithDate(@NotNull Date date){
        return new ResponseEntity<>(availabilityService.availabilityCheckWithDate(date), HttpStatus.OK);
    }

    @GetMapping("/availableCleanerProfessionalsWithDateAndTime")
    public ResponseEntity<List<CleanerProfessional>> availableCleanerProfessionalsWithDateAndTime(@NotNull Date date ,@NotNull int hour ,@NotNull int minute ,@NotNull int duration){
        LocalTime startTime = LocalTime.of(hour,minute);
        LocalTime endTime = LocalTime.of(hour+duration,minute);
        return new ResponseEntity<>(availabilityService.availableCleanerProfessionalsWithDateAndTime(date,startTime,endTime),HttpStatus.OK);
    }

}

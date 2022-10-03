package com.yahya.justlife.responses;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class AvailableTime {

    LocalTime startTime ;

    LocalTime endTime ;
}

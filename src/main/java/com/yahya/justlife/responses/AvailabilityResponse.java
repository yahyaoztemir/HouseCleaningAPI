package com.yahya.justlife.responses;

import com.yahya.justlife.entities.CleanerProfessional;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AvailabilityResponse {

    CleanerProfessional cleanerProfessional;

    List<AvailableTime> availableTimeList = new ArrayList<>();
}

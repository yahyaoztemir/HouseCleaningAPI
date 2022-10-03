package com.yahya.justlife.controllers;

import com.yahya.justlife.entities.CleanerProfessional;
import com.yahya.justlife.services.CleanerProfessionalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cleanerprofessionals")
public class CleanerProfessionalController {

    private CleanerProfessionalService cleanerProfessionalService ;

    public CleanerProfessionalController(CleanerProfessionalService cleanerProfessionalService) {
        this.cleanerProfessionalService = cleanerProfessionalService;
    }

    @GetMapping
    public ResponseEntity<List<CleanerProfessional>> getAllCleanerProfessionals(){
        return new ResponseEntity<>(cleanerProfessionalService.getAllCleanerProfessionals(), HttpStatus.OK);
    }


}

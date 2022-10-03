package com.yahya.justlife.services;


import com.yahya.justlife.entities.Car;
import com.yahya.justlife.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository ;

    public List<Car> getAllCars(){
        return carRepository.findAll();
    }

}

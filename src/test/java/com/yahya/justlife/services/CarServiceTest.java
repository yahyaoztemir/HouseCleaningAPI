package com.yahya.justlife.services;

import com.yahya.justlife.entities.Car;
import com.yahya.justlife.entities.CleanerProfessional;
import com.yahya.justlife.repositories.CarRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
class CarServiceTest {

    @InjectMocks
    private CarService carService ;

    @Mock
    private CarRepository carRepository;

    @Test
    void getAllCars() {

        List<Car> cars = new ArrayList<>();
        Car car = new Car();
        car.setId(1L);
        car.setCleanerProfessionalList(new ArrayList<>());
        CleanerProfessional cleanerProfessional = new CleanerProfessional();
        cleanerProfessional.setName("Test Cleaner Professional");
        cars.add(car);
        given(carRepository.findAll()).willReturn(cars);

        List<Car> expected = carService.getAllCars();

        assertEquals(expected, cars);
    }
}
package com.yahya.justlife.repositories;

import com.yahya.justlife.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car,Long> {
}

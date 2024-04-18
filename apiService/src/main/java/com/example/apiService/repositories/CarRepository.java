package com.example.apiService.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.apiService.models.Cars.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer>{
    Optional<Car> findByNumberCar(String numberCar);
    boolean existsByNumberCar(String numberCar);
}

package com.example.dataService.services;

import org.springframework.stereotype.Service;

import com.example.dataService.models.Cars.Car;
import org.springframework.transaction.annotation.Transactional;

import com.example.dataService.repositories.CarRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;

    @Transactional
    public String saveCar(Car c) {
        if (c.getNumberCar().isEmpty() || c.getBrand().isEmpty()
                || c.getCountry().isEmpty() || c.getModel().isEmpty()) {
            return "Car not added";
        }

        for (Car elemC : carRepository.findAll()) {
            if (c.getNumberCar().equals(elemC.getNumberCar())) {
                return "Car not added";
            }
        }

        carRepository.save(c);

        return "Car added";
    }
}

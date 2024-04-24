package com.example.dataService.services;

import java.util.UUID;

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
        UUID uuid = UUID.randomUUID();

        c.setNumberCar(uuid.toString());

        if (c.getNumberCar().isEmpty() || c.getBrand().isEmpty()
                || c.getCountry().isEmpty() || c.getModel().isEmpty()) {
            return "Car not added";
        }

        carRepository.save(c);

        return "Car added";
    }
}

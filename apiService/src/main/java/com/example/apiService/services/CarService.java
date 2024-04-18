package com.example.apiService.services;

import org.springframework.stereotype.Service;

import com.example.apiService.models.Cars.Car;
import com.example.apiService.dto.MessageRes;
import com.example.apiService.exceptions.CarNotFoundException;
import com.example.apiService.repositories.CarRepository;
import lombok.RequiredArgsConstructor;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;

    public List<Car> getAll() {
        return carRepository.findAll();
    }

    public Car getByNumberCar(String numberCar) {
        return carRepository.findByNumberCar(numberCar)
                .orElseThrow(() -> new CarNotFoundException("Car not found with numberCar=" + numberCar));
    }

    public MessageRes getByStatusNumberCar(String numberCar) {
        if (carRepository.findByNumberCar(numberCar).isEmpty()){
            return new MessageRes("Accepted");
        }
        return new MessageRes("OK");
    }
}

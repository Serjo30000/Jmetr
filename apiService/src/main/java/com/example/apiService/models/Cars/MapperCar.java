package com.example.apiService.models.Cars;

import org.springframework.stereotype.Component;

@Component
public class MapperCar {
    public Car map(CarDto dto) {
        return Car.builder()
                .numberCar(dto.getNumberCar())
                .brand(dto.getBrand())
                .country(dto.getCountry())
                .model(dto.getModel())
                .build();
    }

    public CarDto map(Car dto) {
        return CarDto.builder()
                .numberCar(dto.getNumberCar())
                .brand(dto.getBrand())
                .country(dto.getCountry())
                .model(dto.getModel())
                .build();
    }
}

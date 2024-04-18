package com.example.dataService.models.Cars;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder(toBuilder = true)
public class CarDto {
    private String numberCar;
    private String brand;
    private String country;
    private String model;
}

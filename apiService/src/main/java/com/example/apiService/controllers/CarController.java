package com.example.apiService.controllers;

import java.util.List;


import java.util.concurrent.ExecutionException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.apiService.models.Cars.CarDto;
import com.example.apiService.models.Cars.MapperCar;
import com.example.apiService.dto.MessageRes;
import com.example.apiService.services.CarService;
import com.example.apiService.services.KafkaMessagePublisher;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/data/cars")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;
    private final MapperCar mapperCar;
    private final KafkaMessagePublisher kafkaMessagePublisher;

    @GetMapping
    public ResponseEntity<List<CarDto>> getAll() {
        return ResponseEntity.ok(carService.getAll().stream()
                .map(mapperCar::map)
                .toList());
    }

    @GetMapping("/{numberCar}")
    public ResponseEntity<CarDto> getByNumberCar(@PathVariable("numberCar") String numberCar) {
        return ResponseEntity.ok(mapperCar.map(carService.getByNumberCar(numberCar)));
    }

    @PostMapping("/request")
    public ResponseEntity<MessageRes> addCar(@RequestBody CarDto dto) {
        try {
            kafkaMessagePublisher.sendToCarTopic(dto.getNumberCar(), dto);
            return ResponseEntity.ok(new MessageRes("Accepted"));
        } catch (InterruptedException | ExecutionException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MessageRes("Internal server error"));
        }
    }
}

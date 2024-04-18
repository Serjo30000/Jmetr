package com.example.apiService.controllers;

import java.net.URI;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.servlet.view.RedirectView;

import com.example.apiService.models.Cars.CarDto;
import com.example.apiService.models.Cars.MapperCar;
import com.example.apiService.dto.MessageRes;
import com.example.apiService.services.CarService;
import com.example.apiService.services.KafkaMessageSender;
import org.springframework.beans.factory.annotation.Value;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/data/cars")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;
    private final MapperCar mapperCar;
    private final KafkaMessageSender kafkaMessageSender;
    @Value("${kafka.car.topic}")
    private String topic;

    @GetMapping
    public List<CarDto> getAll() {
        return carService.getAll().stream()
                .map(mapperCar::map)
                .toList();
    }

    @GetMapping("/{numberCar}")
    public CarDto getByNumberCar(@PathVariable("numberCar") String numberCar) {
        return mapperCar.map(carService.getByNumberCar(numberCar));
    }

    @GetMapping("status/{numberCar}")
    public Object getByStatusNumberCar(@PathVariable("numberCar") String numberCar) {
        MessageRes messageRes = carService.getByStatusNumberCar(numberCar);
        if (messageRes.getMsg().equals("OK")) {
            URI location = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/data/cars/{numberCar}")
                    .buildAndExpand(numberCar)
                    .toUri();
            return new RedirectView(location.toString(), true);
        }
        return messageRes;
    }

    @PostMapping("/request")
    public CompletableFuture<MessageRes> addCar(@RequestBody CarDto dto) {
        return kafkaMessageSender.send(topic, dto.getNumberCar(), dto);
    }
}

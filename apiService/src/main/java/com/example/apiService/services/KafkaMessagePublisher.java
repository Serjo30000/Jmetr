package com.example.apiService.services;

import java.util.concurrent.ExecutionException;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.example.apiService.models.Cars.CarDto;
import com.example.apiService.models.ParkingLots.ParkingLotDto;

import org.springframework.beans.factory.annotation.Value;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class KafkaMessagePublisher {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    @Value("${kafka.car.topic}")
    private String carTopic;
    @Value("${kafka.parkinglot.topic}")
    private String parkingLotTopic;

    public void sendToCarTopic(String key, CarDto obj) throws InterruptedException, ExecutionException{
        kafkaTemplate.send(carTopic, key, obj).get();
    }

    public void sendToParkingLotTopic(String key, ParkingLotDto obj) throws InterruptedException, ExecutionException {
        kafkaTemplate.send(parkingLotTopic, key, obj).get();
    }
}

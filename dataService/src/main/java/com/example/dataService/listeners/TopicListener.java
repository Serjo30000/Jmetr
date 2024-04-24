package com.example.dataService.listeners;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.example.dataService.models.Cars.CarDto;
import com.example.dataService.models.Cars.MapperCar;
import com.example.dataService.models.ParkingLots.MapperParkingLot;
import com.example.dataService.models.ParkingLots.ParkingLotDto;
import com.example.dataService.services.CarService;
import com.example.dataService.services.ParkingLotService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class TopicListener {
    private final CarService carService;
    private final ParkingLotService parkingLotService;
    private final MapperCar mapperCar;
    private final MapperParkingLot mapperParkingLot;
    private final ObjectMapper mapper;

    @KafkaListener(topics = "${kafka.car.topic}", concurrency = "4", groupId = "${kafka.consumer.car.id}")
    void consumeMovie(String dto) throws JsonMappingException {
        try {
            var d = mapper.readValue(dto, CarDto.class);
            String res = carService.saveCar(mapperCar.map(d));
            log.info(res);
        } catch (JsonProcessingException e) {
            log.error("Mapping error:", e.getMessage());
        }
    }

    @KafkaListener(topics = "${kafka.parkinglot.topic}", concurrency = "4", groupId = "${kafka.consumer.parkinglot.id}")
    void consumeParkingLot(String dto) throws JsonMappingException {
        try {
            var d = mapper.readValue(dto, ParkingLotDto.class);
            String res = parkingLotService.saveParkingLot(mapperParkingLot.map(d));
            log.info(res);
        } catch (JsonProcessingException e) {
            log.error("Mapping error:", e.getMessage());
        }
    }
}

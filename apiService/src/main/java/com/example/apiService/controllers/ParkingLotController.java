package com.example.apiService.controllers;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.apiService.dto.MessageRes;
import com.example.apiService.models.ParkingLots.MapperParkingLot;
import com.example.apiService.models.ParkingLots.ParkingLotDto;
import com.example.apiService.services.KafkaMessagePublisher;
import com.example.apiService.services.ParkingLotService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/data/parkingLots")
@RequiredArgsConstructor
public class ParkingLotController {
    private final ParkingLotService parkingLotService;
    private final MapperParkingLot mapperParkingLot;
    private final KafkaMessagePublisher kafkaMessagePublisher;

    @GetMapping
    public ResponseEntity<List<ParkingLotDto>> getAll() {
        return ResponseEntity.ok(parkingLotService.getAll().stream()
                .map(mapperParkingLot::map)
                .toList());
    }

    @GetMapping("/{numberParkingLot}")
    public ResponseEntity<ParkingLotDto> getByNumberParkingLot(@PathVariable("numberParkingLot") String numberParkingLot) {
        return ResponseEntity.ok(mapperParkingLot.map(parkingLotService.getByNumberParkingLot(numberParkingLot)));
    }

    @PostMapping("/request")
    public ResponseEntity<MessageRes> addParkingLot(@RequestBody ParkingLotDto dto) {
        try {
            kafkaMessagePublisher.sendToParkingLotTopic(dto.getNumberParkingLot(), dto);
            return ResponseEntity.ok(new MessageRes("Accepted"));
        } catch (InterruptedException | ExecutionException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MessageRes("Internal server error"));
        }
    }
}

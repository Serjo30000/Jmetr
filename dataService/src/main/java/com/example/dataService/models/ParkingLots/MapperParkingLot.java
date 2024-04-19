package com.example.dataService.models.ParkingLots;

import org.springframework.stereotype.Component;

@Component
public class MapperParkingLot {
    public ParkingLot map(ParkingLotDto dto) {
        return ParkingLot.builder()
                .numberParkingLot(dto.getNumberParkingLot())
                .location(dto.getLocation())
                .build();
    }

    public ParkingLotDto map(ParkingLot dto) {
        return ParkingLotDto.builder()
                .numberParkingLot(dto.getNumberParkingLot())
                .location(dto.getLocation())
                .build();
    }
}

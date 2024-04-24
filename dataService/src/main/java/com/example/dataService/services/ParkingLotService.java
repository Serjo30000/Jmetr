package com.example.dataService.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.dataService.models.ParkingLots.ParkingLot;
import com.example.dataService.repositories.ParkingLotRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ParkingLotService {
    private final ParkingLotRepository parkingLotRepository;

    @Transactional
    public String saveParkingLot(ParkingLot pL) {
        UUID uuid = UUID.randomUUID();

        pL.setNumberParkingLot(uuid.toString());

        if (pL.getNumberParkingLot().isEmpty() || pL.getLocation().isEmpty()) {
            return "ParkingLot not added";
        }

        parkingLotRepository.save(pL);

        return "ParkingLot added";
    }
}

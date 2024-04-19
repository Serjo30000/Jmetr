package com.example.dataService.services;

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
        if (pL.getNumberParkingLot().isEmpty() || pL.getLocation().isEmpty()) {
            return "ParkingLot not added";
        }

        for (ParkingLot elemPl : parkingLotRepository.findAll()) {
            if (pL.getNumberParkingLot().equals(elemPl.getNumberParkingLot())) {
                return "ParkingLot not added";
            }
        }

        parkingLotRepository.save(pL);

        return "ParkingLot added";
    }
}

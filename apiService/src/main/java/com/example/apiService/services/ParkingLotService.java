package com.example.apiService.services;

import org.springframework.stereotype.Service;

import com.example.apiService.models.ParkingLots.ParkingLot;
import com.example.apiService.repositories.ParkingLotRepository;
import com.example.apiService.exceptions.ParkingLotNotFoundException;
import lombok.RequiredArgsConstructor;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ParkingLotService {
    private final ParkingLotRepository parkingLotRepository;

    public List<ParkingLot> getAll() {
        return parkingLotRepository.findAll();
    }

    public ParkingLot getByNumberParkingLot(String numberParkingLot) {
        return parkingLotRepository.findByNumberParkingLot(numberParkingLot)
                .orElseThrow(() -> new ParkingLotNotFoundException("ParkingLot not found with numberParkingLot=" + numberParkingLot));
    }
}

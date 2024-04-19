package com.example.apiService.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.apiService.models.ParkingLots.ParkingLot;

@Repository
public interface ParkingLotRepository extends JpaRepository<ParkingLot, Integer>{
    Optional<ParkingLot> findByNumberParkingLot(String numberParkingLot);
    boolean existsByNumberParkingLot(String numberParkingLot);
}

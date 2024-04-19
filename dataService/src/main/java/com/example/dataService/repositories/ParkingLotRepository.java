package com.example.dataService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.dataService.models.ParkingLots.ParkingLot;


@Repository
public interface ParkingLotRepository extends JpaRepository<ParkingLot, Integer>{

}

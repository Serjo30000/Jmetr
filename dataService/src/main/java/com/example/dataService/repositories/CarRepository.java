package com.example.dataService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.dataService.models.Cars.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer>{

}

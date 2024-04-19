package com.example.apiService.exceptions;

public class ParkingLotNotFoundException extends RuntimeException{
    public ParkingLotNotFoundException(String e){
        super(e);
    }
}

package com.example.apiService.exceptions;

public class CarNotFoundException extends RuntimeException{
    public CarNotFoundException(String e){
        super(e);
    }
}

package com.example.apiService.controllers;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.apiService.exceptions.CarNotFoundException;
import com.example.apiService.exceptions.ParkingLotNotFoundException;
import com.example.apiService.exceptions.dto.ResponseError;

@ControllerAdvice
public class AppControllerAdvice {
    @ExceptionHandler(CarNotFoundException.class)
    public ResponseEntity<ResponseError> handleException(CarNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ResponseError(e.getMessage(), LocalDateTime.now().toString()));
    }

    @ExceptionHandler(ParkingLotNotFoundException.class)
    public ResponseEntity<ResponseError> handleException(ParkingLotNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ResponseError(e.getMessage(), LocalDateTime.now().toString()));
    }
}

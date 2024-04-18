package com.example.apiService.controllers;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.apiService.exceptions.CarNotFoundException;
import com.example.apiService.exceptions.dto.ResponseError;

@ControllerAdvice
public class AppControllerAdvice {
    @ExceptionHandler(CarNotFoundException.class)
    public ResponseError handleException(CarNotFoundException e) {
        return new ResponseError(e.getMessage(), LocalDateTime.now().toString());
    }
}

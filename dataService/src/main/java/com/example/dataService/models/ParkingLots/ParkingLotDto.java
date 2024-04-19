package com.example.dataService.models.ParkingLots;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder(toBuilder = true)
public class ParkingLotDto {
    private String numberParkingLot;
    private String location;
}

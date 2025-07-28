package com.fleet.management.common_contracts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDto {
    private Long id;
    private String licensePlate;
    private String make;
    private String model;
    private Integer year;
    private String status;
    private Double currentLatitude;
    private Double currentLongitude;
    private Double currentFuelLevelLiter;
}

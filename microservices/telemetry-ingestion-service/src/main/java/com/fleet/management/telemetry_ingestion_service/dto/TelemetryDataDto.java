package com.fleet.management.telemetry_ingestion_service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TelemetryDataDto {

    @NotNull(message = "Vehicle ID cannot be null")
    private Long vehicleId;

    @NotNull(message = "Timestamp cannot be null")
    private LocalDateTime timestamp;

    @NotNull(message = "Latitude cannot be null")
    private Double latitude;

    @NotNull(message = "Longitude cannot be null")
    private Double longitude;

    private Double speedKmh;
    private Double fuelLevelLiter;
    private String vehicleStatus;
}

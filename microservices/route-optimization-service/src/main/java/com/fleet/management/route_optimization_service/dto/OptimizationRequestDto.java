package com.fleet.management.route_optimization_service.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OptimizationRequestDto {
    @NotNull(message = "Vehicle ID cannot be null")
    private Long vehicleId;
    @NotNull(message = "Start location cannot be null")
    private LocationDto startLocation;
    @NotEmpty(message = "Destination locations cannot be empty")
    private List<LocationDto> destinationLocations;
}

package com.fleet.management.route_optimization_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OptimizationResultDto {
    private Long vehicleId;
    private String optimizedRouteId;
    private List<LocationDto> optimizedWaypoints;
    private Double estimatedDistanceKm;
    private Double estimatedTravelTimeMinutes;
    private LocalDateTime optimizationTimestamp;
    private String status;
    private String message;
}


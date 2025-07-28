package com.fleet.management.route_optimization_service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationDto {
    @NotNull
    private Double latitude;
    @NotNull
    private Double longitude;
    private String address;
}


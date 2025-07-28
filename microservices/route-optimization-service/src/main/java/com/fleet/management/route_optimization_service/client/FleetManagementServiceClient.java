package com.fleet.management.route_optimization_service.client;

import com.fleet.management.common_contracts.dto.VehicleDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "fleet-management-service", url = "${fleet-management-service.url}")
public interface FleetManagementServiceClient {

    @GetMapping("/api/vehicles/{id}")
    VehicleDto getVehicleById(@PathVariable("id") Long id);
}

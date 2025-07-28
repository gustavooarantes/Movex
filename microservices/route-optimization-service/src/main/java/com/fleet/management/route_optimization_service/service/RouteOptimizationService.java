package com.fleet.management.route_optimization_service.service;

import com.fleet.management.route_optimization_service.client.FleetManagementServiceClient;
import com.fleet.management.route_optimization_service.config.RabbitMQConfig;
import com.fleet.management.common_contracts.dto.LocationDto;
import com.fleet.management.common_contracts.dto.OptimizationRequestDto;
import com.fleet.management.common_contracts.dto.OptimizationResultDto;
import com.fleet.management.common_contracts.dto.VehicleDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class RouteOptimizationService {

    private final FleetManagementServiceClient fleetManagementServiceClient;
    private final RabbitTemplate rabbitTemplate;

    public RouteOptimizationService(FleetManagementServiceClient fleetManagementServiceClient,
                                    RabbitTemplate rabbitTemplate) {
        this.fleetManagementServiceClient = fleetManagementServiceClient;
        this.rabbitTemplate = rabbitTemplate;
    }

    public OptimizationResultDto optimizeRoute(OptimizationRequestDto request) {
        System.out.println("Received optimization request for Vehicle ID: " + request.getVehicleId());

        VehicleDto vehicle = null;
        try {
            vehicle = fleetManagementServiceClient.getVehicleById(request.getVehicleId());
            System.out.println("Vehicle details retrieved: " + vehicle);
        } catch (Exception e) {
            System.err.println("Error fetching vehicle " + request.getVehicleId() + ": " + e.getMessage());
            return buildErrorResult(request.getVehicleId(), "Failed to retrieve vehicle details: " + e.getMessage());
        }

        if (vehicle == null) {
            return buildErrorResult(request.getVehicleId(), "Vehicle not found.");
        }

        // Lógica de Otimização de Rota (Simulada)
        // Para demonstração, irei apenas "ordenar" os destinos e calcular uma distância/tempo fictício.

        List<LocationDto> optimizedWaypoints = new ArrayList<>();
        optimizedWaypoints.add(request.getStartLocation());
        optimizedWaypoints.addAll(request.getDestinationLocations());

        double estimatedDistanceKm = calculateFictionalDistance(optimizedWaypoints);
        double estimatedTravelTimeMinutes = estimatedDistanceKm / 60.0 * 60; // Assumindo 60km/h de média

        OptimizationResultDto result = new OptimizationResultDto(
                request.getVehicleId(),
                UUID.randomUUID().toString(),
                optimizedWaypoints,
                estimatedDistanceKm,
                estimatedTravelTimeMinutes,
                LocalDateTime.now(),
                "SUCCESS",
                "Route optimized successfully (simulated)."
        );

        rabbitTemplate.convertAndSend(RabbitMQConfig.ROUTE_OPTIMIZATION_RESULTS_QUEUE, result);
        System.out.println("Optimization result published to RabbitMQ for Vehicle ID: " + request.getVehicleId());

        return result;
    }

    private double calculateFictionalDistance(List<LocationDto> locations) {
        if (locations == null || locations.size() < 2) {
            return 0.0;
        }
        double totalDistance = 0.0;
        for (int i = 0; i < locations.size() - 1; i++) {
            // Distância fictícia baseada na diferença de lat/lon
            double latDiff = Math.abs(locations.get(i).getLatitude() - locations.get(i+1).getLatitude());
            double lonDiff = Math.abs(locations.get(i).getLongitude() - locations.get(i+1).getLongitude());
            totalDistance += (latDiff + lonDiff) * 100; // Multiplicador para tornar a distância mais "realista"
        }
        return totalDistance;
    }

    private OptimizationResultDto buildErrorResult(Long vehicleId, String message) {
        return new OptimizationResultDto(
                vehicleId,
                null,
                null,
                null,
                null,
                LocalDateTime.now(),
                "FAILED",
                message
        );
    }
}


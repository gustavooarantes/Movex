package com.fleet.management.route_optimization_service.controller;

import com.fleet.management.route_optimization_service.dto.OptimizationRequestDto;
import com.fleet.management.route_optimization_service.dto.OptimizationResultDto;
import com.fleet.management.route_optimization_service.service.RouteOptimizationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/routes")
public class RouteOptimizationController {

    private final RouteOptimizationService routeOptimizationService;

    public RouteOptimizationController(RouteOptimizationService routeOptimizationService) {
        this.routeOptimizationService = routeOptimizationService;
    }

    @PostMapping("/optimize")
    public ResponseEntity<OptimizationResultDto> optimizeRoute(@Valid @RequestBody OptimizationRequestDto request) {
        OptimizationResultDto result = routeOptimizationService.optimizeRoute(request);
        return ResponseEntity.ok(result);
    }
}

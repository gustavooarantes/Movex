package com.fleet.management.fleet_management_service.service;

import com.fleet.management.fleet_management_service.entity.Vehicle;
import com.fleet.management.fleet_management_service.repository.VehicleRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public Vehicle saveVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Optional<Vehicle> getVehicleById(Long id) {
        return vehicleRepository.findById(id);
    }

    public Vehicle updateVehicle(Long id, Vehicle vehicleDetails) {
        return vehicleRepository.findById(id)
                .map(existingVehicle -> {
                    existingVehicle.setLicensePlate(vehicleDetails.getLicensePlate());
                    existingVehicle.setMake(vehicleDetails.getMake());
                    existingVehicle.setModel(vehicleDetails.getModel());
                    existingVehicle.setYear(vehicleDetails.getYear());
                    existingVehicle.setColor(vehicleDetails.getColor());
                    existingVehicle.setVehicleType(vehicleDetails.getVehicleType());
                    existingVehicle.setMileage(vehicleDetails.getMileage());
                    existingVehicle.setLastMaintenanceDate(vehicleDetails.getLastMaintenanceDate());
                    return vehicleRepository.save(existingVehicle);
                })
                .orElse(null);
    }

    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }
}

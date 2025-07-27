package com.fleet.management.fleet_management_service.service;

import com.fleet.management.fleet_management_service.entity.Driver;
import com.fleet.management.fleet_management_service.repository.DriverRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DriverService {

    private final DriverRepository driverRepository;

    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    public Driver saveDriver(Driver driver) {
        return driverRepository.save(driver);
    }

    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    public Optional<Driver> getDriverById(Long id) {
        return driverRepository.findById(id);
    }

    public Driver updateDriver(Long id, Driver driverDetails) {
        return driverRepository.findById(id)
                .map(existingDriver -> {
                    existingDriver.setFirstName(driverDetails.getFirstName());
                    existingDriver.setLastName(driverDetails.getLastName());
                    existingDriver.setLicenseNumber(driverDetails.getLicenseNumber());
                    existingDriver.setDateOfBirth(driverDetails.getDateOfBirth());
                    existingDriver.setContactNumber(driverDetails.getContactNumber());
                    existingDriver.setEmail(driverDetails.getEmail());
                    existingDriver.setHireDate(driverDetails.getHireDate());
                    existingDriver.setStatus(driverDetails.getStatus());
                    return driverRepository.save(existingDriver);
                })
                .orElse(null);
    }

    public void deleteDriver(Long id) {
        driverRepository.deleteById(id);
    }
}
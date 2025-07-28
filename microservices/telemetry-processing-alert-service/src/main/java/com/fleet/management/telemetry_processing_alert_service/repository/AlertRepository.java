package com.fleet.management.telemetry_processing_alert_service.repository;

import com.fleet.management.telemetry_processing_alert_service.model.Alert;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertRepository extends MongoRepository<Alert, String> {
}

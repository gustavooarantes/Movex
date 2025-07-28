package com.fleet.management.telemetry_processing_alert_service.repository;

import com.fleet.management.telemetry_processing_alert_service.model.TelemetryRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelemetryRecordRepository extends MongoRepository<TelemetryRecord, String> {
}

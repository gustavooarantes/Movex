package com.fleet.management.telemetry_processing_alert_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "telemetry_records")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TelemetryRecord {

    @Id
    private String id;

    private Long vehicleId;
    private LocalDateTime timestamp;
    private Double latitude;
    private Double longitude;
    private Double speedKmh;
    private Double fuelLevelLiter;
    private String vehicleStatus;
    private Boolean processed;
}

package com.fleet.management.telemetry_processing_alert_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "alerts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Alert {

    @Id
    private String id;

    private Long vehicleId;
    private LocalDateTime timestamp;
    private String type;
    private String description;
    private Double latitude;
    private Double longitude;
    private String status;
}

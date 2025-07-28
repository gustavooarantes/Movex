package com.fleet.management.telemetry_processing_alert_service.service;

import com.fleet.management.telemetry_processing_alert_service.config.RabbitMQConfig;
import com.fleet.management.common_contracts.dto.TelemetryDataDto;
import com.fleet.management.telemetry_processing_alert_service.model.Alert;
import com.fleet.management.telemetry_processing_alert_service.model.TelemetryRecord;
import com.fleet.management.telemetry_processing_alert_service.repository.AlertRepository;
import com.fleet.management.telemetry_processing_alert_service.repository.TelemetryRecordRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TelemetryProcessingService {

    private final TelemetryRecordRepository telemetryRecordRepository;
    private final AlertRepository alertRepository;
    private final RabbitTemplate rabbitTemplate;

    public TelemetryProcessingService(TelemetryRecordRepository telemetryRecordRepository,
                                      AlertRepository alertRepository,
                                      RabbitTemplate rabbitTemplate) {
        this.telemetryRecordRepository = telemetryRecordRepository;
        this.alertRepository = alertRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = RabbitMQConfig.TELEMETRY_RAW_QUEUE)
    public void processTelemetryMessage(TelemetryDataDto telemetryData) {
        System.out.println("Received telemetry message for Vehicle ID: " + telemetryData.getVehicleId());
        System.out.println("Timestamp: " + telemetryData.getTimestamp() + ", Speed: " + telemetryData.getSpeedKmh());

        TelemetryRecord record = new TelemetryRecord();
        record.setVehicleId(telemetryData.getVehicleId());
        record.setTimestamp(telemetryData.getTimestamp());
        record.setLatitude(telemetryData.getLatitude());
        record.setLongitude(telemetryData.getLongitude());
        record.setSpeedKmh(telemetryData.getSpeedKmh());
        record.setFuelLevelLiter(telemetryData.getFuelLevelLiter());
        record.setVehicleStatus(telemetryData.getVehicleStatus());
        record.setProcessed(true);
        telemetryRecordRepository.save(record);
        System.out.println("Telemetry record saved to MongoDB for Vehicle ID: " + telemetryData.getVehicleId());

        checkForAlerts(telemetryData);
    }

    private void checkForAlerts(TelemetryDataDto telemetryData) {
        if (telemetryData.getSpeedKmh() != null && telemetryData.getSpeedKmh() > 80.0) {
            createAndPublishAlert(telemetryData, "SPEED_EXCESS", "Vehicle exceeded speed limit (80 km/h).");
        }

        if (telemetryData.getFuelLevelLiter() != null && telemetryData.getFuelLevelLiter() < 10.0) {
            createAndPublishAlert(telemetryData, "LOW_FUEL", "Vehicle fuel level is critically low.");
        }

    }

    private void createAndPublishAlert(TelemetryDataDto telemetryData, String type, String description) {
        Alert alert = new Alert();
        alert.setVehicleId(telemetryData.getVehicleId());
        alert.setTimestamp(LocalDateTime.now());
        alert.setType(type);
        alert.setDescription(description);
        alert.setLatitude(telemetryData.getLatitude());
        alert.setLongitude(telemetryData.getLongitude());
        alert.setStatus("NEW");

        alertRepository.save(alert);
        System.out.println("Alert generated and saved: " + type + " for Vehicle ID: " + telemetryData.getVehicleId());

        rabbitTemplate.convertAndSend(RabbitMQConfig.ALERTS_GENERATED_QUEUE, alert);
        System.out.println("Alert published to RabbitMQ.");
    }
}

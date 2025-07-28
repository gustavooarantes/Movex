package com.fleet.management.telemetry_ingestion_service.controller;

import com.fleet.management.telemetry_ingestion_service.config.RabbitMQConfig;
import com.fleet.management.common_contracts.dto.TelemetryDataDto;
import jakarta.validation.Valid;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/telemetry")
public class TelemetryController {

    private final RabbitTemplate rabbitTemplate;

    public TelemetryController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping("/ingest")
    public ResponseEntity<String> ingestTelemetry(@Valid @RequestBody TelemetryDataDto telemetryData) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.TELEMETRY_RAW_QUEUE, telemetryData);
        return new ResponseEntity<>("Telemetry received and queued.", HttpStatus.ACCEPTED);
    }
}

package com.fleet.management.notification_service.service;

import com.fleet.management.notification_service.config.RabbitMQConfig;
import com.fleet.management.notification_service.model.NotificationLog;
import com.fleet.management.notification_service.repository.NotificationLogRepository;

import com.fleet.management.common_contracts.dto.OptimizationResultDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotificationService {

    private final NotificationLogRepository notificationLogRepository;

    public NotificationService(NotificationLogRepository notificationLogRepository) {
        this.notificationLogRepository = notificationLogRepository;
    }

    @RabbitListener(queues = RabbitMQConfig.ALERTS_GENERATED_QUEUE)
    public void handleAlertNotification(Object alertPayload) {
        System.out.println("ALERT RECEIVED for notification: " + alertPayload.toString());
        String message = "New alert: " + alertPayload.toString();
        logAndSendNotification("ALERT", "admin@movex.com", message, "ALERT", null);
    }

    @RabbitListener(queues = RabbitMQConfig.ROUTE_OPTIMIZATION_RESULTS_QUEUE)
    public void handleRouteOptimizationResultNotification(OptimizationResultDto result) {
        System.out.println("ROUTE OPTIMIZATION RESULT RECEIVED for notification: " + result.toString());
        String message = "Route optimized for vehicle " + result.getVehicleId() + ". Est. time: " + result.getEstimatedTravelTimeMinutes() + " mins.";
        logAndSendNotification("ROUTE_OPTIMIZATION", "dispatcher@movex.com", message, "ROUTE_OPTIMIZATION_RESULT", result.getOptimizedRouteId());
    }

    private void logAndSendNotification(String eventType, String recipient, String message, String relatedEntityType, String relatedEntityId) {
        // Simula o envio de uma notificação (ex: email, SMS, push)
        System.out.println("--- Sending Notification ---");
        System.out.println("Type: " + eventType);
        System.out.println("To: " + recipient);
        System.out.println("Message: " + message);
        System.out.println("--------------------------");

        NotificationLog log = new NotificationLog();
        log.setEventType(eventType);
        log.setRecipient(recipient);
        log.setMessage(message);
        log.setTimestamp(LocalDateTime.now());
        log.setStatus("SENT");
        log.setRelatedEntityType(relatedEntityType);
        log.setRelatedEntityId(relatedEntityId);
        notificationLogRepository.save(log);
        System.out.println("Notification logged to MongoDB.");
    }
}

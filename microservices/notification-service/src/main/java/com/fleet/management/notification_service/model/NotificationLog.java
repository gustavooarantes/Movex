package com.fleet.management.notification_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "notification_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationLog {

    @Id
    private String id;
    private String eventType;
    private String recipient;
    private String message;
    private LocalDateTime timestamp;
    private String status;
    private String relatedEntityId;
    private String relatedEntityType;
}

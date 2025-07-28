# Movex - Real-time Fleet Optimization and Tracking System
## Developer: Gustavo Oliveira Arantes

A robust, microservices-based system designed for real-time fleet management, tracking, and route optimization.

Movex demonstrates a modern, scalable architecture, employing asynchronous communication and diverse data persistence strategies to address critical operational challenges.

---
## Project Structure
<pre>
└── common-contracts/
    └── src/
        └── main/
            └── java/
                └── com/
                    └── fleet/
                        └── management/
                            └── common_contracts/
                                └── dto/
                                    └── TelemetryDataDto.java
                                    └── OptimizationRequestDto.java
                                    └── OptimizationResultDto.java
                                    └── LocationDto.java
└── microservices/
    ├── fleet-management-service/
    │   ├── src/
    │   │   ├── main/
    │   │   │   ├── java/
    │   │   │   │   └── com/fleet/management/fleet_management_service/
    │   │   │   │       ├── config/
    │   │   │   │       ├── controller/
    │   │   │   │       ├── entity/
    │   │   │   │       ├── repository/
    │   │   │   │       └── service/
    │   │   │   └── resources/
    │   │   └── test/
    │   ├── Dockerfile
    │   └── k8s/
    │       ├── deployment.yaml
    │       └── service.yaml
    ├── telemetry-ingestion-service/
    │   ├── src/
    │   │   ├── main/
    │   │   │   ├── java/
    │   │   │   │   └── com/fleet/management/telemetry_ingestion_service/
    │   │   │   │       ├── config/
    │   │   │   │       └── controller/
    │   │   │   └── resources/
    │   │   └── test/
    │   ├── Dockerfile
    │   └── k8s/
    │       ├── deployment.yaml
    │       └── service.yaml
    ├── telemetry-processing-alert-service/
    │   ├── src/
    │   │   ├── main/
    │   │   │   ├── java/
    │   │   │   │   └── com/fleet/management/telemetry_processing_alert_service/
    │   │   │   │       ├── config/
    │   │   │   │       ├── model/
    │   │   │   │       ├── repository/
    │   │   │   │       └── service/
    │   │   │   └── resources/
    │   │   └── test/
    │   ├── Dockerfile
    │   └── k8s/
    │       ├── deployment.yaml
    │       └── service.yaml
    ├── route-optimization-service/
    │   ├── src/
    │   │   ├── main/
    │   │   │   ├── java/
    │   │   │   │   └── com/fleet/management/route_optimization_service/
    │   │   │   │       ├── client/
    │   │   │   │       ├── config/
    │   │   │   │       ├── dto/
    │   │   │   │       └── service/
    │   │   │   └── resources/
    │   │   └── test/
    │   ├── Dockerfile
    │   └── k8s/
    │       ├── deployment.yaml
    │       └── service.yaml
    └── notification-service/
        ├── src/
        │   ├── main/
        │   │   ├── java/
        │   │   │   └── com/fleet/management/notification_service/
        │   │   │       ├── config/
        │   │   │       ├── model/
        │   │   │       ├── repository/
        │   │   │       └── service/
        │   │   │   └── resources/
        │   │   └── test/
        │   ├── Dockerfile
        │   └── k8s/
        │       ├── deployment.yaml
        │       └── service.yaml
</pre>
---

## Features

* **Microservices Architecture**: Designed as a set of independently deployable services to enhance scalability, resilience, and maintainability.
* **Asynchronous Communication with RabbitMQ**: Events and messages are exchanged asynchronously between services, ensuring loose coupling and efficient processing.
* **Diverse Data Persistence**: Utilizes **PostgreSQL** for relational data (e.g., fleet management) and **MongoDB** for flexible, non-relational data (e.g., telemetry records, notification logs).
* **Real-time Telemetry Ingestion & Processing**: Ingests high-volume telemetry data, processes it in real-time, and generates actionable alerts based on defined rules.
* **Inter-Service Communication with OpenFeign**: Uses OpenFeign client for declarative, synchronous HTTP calls between microservices (e.g., Route Optimization fetching vehicle details from Fleet Management).
* **Route Optimization**: Simulates route optimization based on vehicle and destination data, publishing results for further actions.
* **Event-Driven Notifications**: Consumes alerts and optimization results to simulate sending notifications, logging each event.
* **Containerization with Docker**: Each microservice is containerized using optimized Dockerfiles for consistent environments.
* **Local Orchestration with Kubernetes (Minikube)**: Demonstrates deployment and management of the entire microservices architecture on a local Kubernetes cluster.

---

## Tech Stack

* **Backend**: Java 17, Spring Boot 3
* **Build Tool**: Apache Maven
* **Messaging**: RabbitMQ
* **Data Persistence**: Spring Data JPA, PostgreSQL, Spring Data MongoDB, MongoDB
* **Inter-Service Communication**: REST APIs, OpenFeign
* **Containerization**: Docker
* **Orchestration**: Kubernetes (Minikube)
* **Utilities**: Lombok, Jakarta Validation

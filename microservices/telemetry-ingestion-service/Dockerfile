# --- BUILD STAGE ---
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

COPY pom.xml .
COPY microservices/fleet-management-service/pom.xml microservices/fleet-management-service/
COPY microservices/telemetry-ingestion-service/pom.xml microservices/telemetry-ingestion-service/
COPY microservices/telemetry-processing-alert-service/pom.xml microservices/telemetry-processing-alert-service/
COPY microservices/route-optimization-service/pom.xml microservices/route-optimization-service/
COPY microservices/notification-service/pom.xml microservices/notification-service/
COPY common-contracts/pom.xml common-contracts/

COPY microservices/fleet-management-service/src microservices/fleet-management-service/src
COPY microservices/telemetry-ingestion-service/src microservices/telemetry-ingestion-service/src
COPY microservices/telemetry-processing-alert-service/src microservices/telemetry-processing-alert-service/src
COPY microservices/route-optimization-service/src microservices/route-optimization-service/src
COPY microservices/notification-service/src microservices/notification-service/src
COPY common-contracts/src common-contracts/src

RUN mvn clean package -DskipTests

# --- RUNTIME STAGE ---
FROM eclipse-temurin:17-jre-alpine AS runtime
WORKDIR /app

COPY --from=build /app/microservices/telemetry-ingestion-service/target/telemetry-ingestion-service-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]
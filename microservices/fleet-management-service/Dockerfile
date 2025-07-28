# --- BUILD STAGE ---
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copy all pom.xml files first to leverage Docker cache
COPY pom.xml .
COPY microservices/fleet-management-service/pom.xml microservices/fleet-management-service/
COPY microservices/telemetry-ingestion-service/pom.xml microservices/telemetry-ingestion-service/
COPY microservices/telemetry-processing-alert-service/pom.xml microservices/telemetry-processing-alert-service/
COPY microservices/route-optimization-service/pom.xml microservices/route-optimization-service/
COPY microservices/notification-service/pom.xml microservices/notification-service/
COPY common-contracts/pom.xml common-contracts/

# Copy source directories for each module
COPY microservices/fleet-management-service/src microservices/fleet-management-service/src
COPY microservices/telemetry-ingestion-service/src microservices/telemetry-ingestion-service/src
COPY microservices/telemetry-processing-alert-service/src microservices/telemetry-processing-alert-service/src
COPY microservices/route-optimization-service/src microservices/route-optimization-service/src
COPY microservices/notification-service/src microservices/notification-service/src
COPY common-contracts/src common-contracts/src

# Package the application (skip tests for faster build)
RUN mvn clean package -DskipTests

# --- RUNTIME STAGE ---
FROM eclipse-temurin:17-jre-alpine AS runtime
WORKDIR /app

# Copy the JAR from the build stage for this specific service
COPY --from=build /app/microservices/fleet-management-service/target/fleet-management-service-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
# Sistema de Otimização e Rastreamento de Frota em Tempo Real

Este projeto visa desenvolver uma plataforma de microsserviços para o gerenciamento, otimização e monitoramento em tempo real de uma frota de veículos.

## Arquitetura

O sistema é construído sobre uma arquitetura de microsserviços, comunicando-se via APIs REST e mensageria (RabbitMQ).

### Microsserviços:
* **Fleet Management Service:** Gerenciamento de veículos e motoristas.
* **Telemetry Ingestion Service:** Ingestão de dados de telemetria em tempo real.
* **Telemetry Processing & Alert Service:** Processamento de telemetria e geração de alertas.
* **Route Optimization Service:** Otimização de rotas.
* **Reporting & Dashboard Service:** Relatórios e visualização de dados da frota.

### Tecnologias Principais:
* **Backend:** Java 17, Spring Boot, Spring Data JPA/Hibernate.
* **Mensageria:** RabbitMQ.
* **Bancos de Dados:** PostgreSQL (relacional), MongoDB (não relacional).
* **Containerização:** Docker.
**Orquestração:** Kubernetes (Minikube)
* **Orquestração (Local):** Docker Compose.
* **CI/CD:** Jenkins.
* **Cloud:** AWS.
* **Observabilidade:** Prometheus, Grafana, ELK Stack.
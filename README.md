<div align="center">

# Ticketing-System

A robust, scalable backend for modern event ticketing and booking!

</div>

---

## Overview

> This project is a microservices-based ticketing system designed to handle event discovery, booking, and order management in a scalable and resilient way.

The system is composed of three core services:

Inventory Service: Manages events, availability, and related metadata
Booking Service: Handles ticket reservations and booking workflows
Order Service: Processes confirmed bookings and manages orders

A shared database is used to store entities such as Users, Events, and Orders, ensuring consistent data access across services.

To support reliability and scalability, the system incorporates:

Keycloak for authentication and authorization
Kafka for asynchronous communication between Booking and Order services
Circuit Breaker pattern to improve fault tolerance and prevent cascading failures



### Directory Structure

```
Ticketing-System/
├── 📁 .idea/
├── 📁 apigateway/
│   ├── ... (Spring Boot application files)
├── 📁 bookingservice/
│   ├── ... (Spring Boot application files)
├── 📁 inventoryservice/
│   ├── ... (Spring Boot application files)
├── 📁 orderservice/
│   ├── ... (Spring Boot application files)
├── 📄 .DS_Store
├── 📄 .gitignore
├── 📄 README.md
```

## Operational Setup

Follow these steps to get the Ticketing-System backend up and running on your local machine.

### Prerequisites

Before you begin, ensure you have the following installed:

*   **Java Development Kit (JDK)**: Version 17 or higher.
    *   [Download JDK](https://www.oracle.com/java/technologies/downloads/)
*   **Maven**: Version 3.6.0 or higher.
    *   [Download Maven](https://maven.apache.org/download.cgi)
*   **Git**: For cloning the repository.
    *   [Download Git](https://git-scm.com/downloads)

### Installation

1.  **Clone the repository:**

    ```bash
    git clone https://github.com/YOUR_GITHUB_USERNAME/Ticketing-System.git
    cd Ticketing-System
    ```

2.  **Build Each Service:**
    Navigate into each service directory and build the project using Maven.

    ```bash
    # Build API Gateway
    cd apigateway
    mvn clean install
    cd ..

    # Build Booking Service
    cd bookingservice
    mvn clean install
    cd ..

    # Build Inventory Service
    cd inventoryservice
    mvn clean install
    cd ..

    # Build Order Service
    cd orderservice
    mvn clean install
    cd ..
    ```

3.  **Run Each Service:**
    Start each Spring Boot application. It's recommended to start the API Gateway last. Each service will typically run on a different port (e.g., 8080, 8081, 8082, 8083).

    ```bash
    # Start Booking Service (e.g., on port 8081)
    cd bookingservice
    mvn spring-boot:run &
    cd ..

    # Start Inventory Service (e.g., on port 8082)
    cd inventoryservice
    mvn spring-boot:run &
    cd ..

    # Start Order Service (e.g., on port 8083)
    cd orderservice
    mvn spring-boot:run &
    cd ..

    # Start API Gateway (e.g., on port 8080)
    cd apigateway
    mvn spring-boot:run &
    cd ..
    ```
    *(Note: The `&` symbol runs the command in the background. You might prefer opening separate terminal windows for each service for better log visibility.)*

### Configuration

Each microservice contains its own `application.properties` or `application.yml` file (typically located in `src/main/resources`). These files are used to configure:

*   **Server Ports**: `server.port`
*   **Database Connections**: `spring.datasource.url`, `username`, `password`
*   **Service Discovery**: Configuration for connecting to a service registry (if used, e.g., Eureka).
*   **API Gateway Routes**: Specific routing rules for the `apigateway` service.



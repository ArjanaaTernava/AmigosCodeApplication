# AmigosCode Course Backend Application

This repository contains a Java-based application developed following a course or tutorial by AmigosCode. It serves as a learning project, demonstrating various concepts in Java, Spring Boot, and potentially Docker.

## Features

- **Spring Boot**: Core framework for application development.
- **Data Access**: Supports JDBC, JPA, and in-memory data management.
- **Exception Handling**: Custom exceptions for better error management.
- **Testing**: Unit and integration tests with Testcontainers.
- **Docker**: Containerization support with Docker Compose.

## Project Structure

- **Main Application**: Contains customer management logic and services.
- **Resources**: Database migration scripts and configuration files.
- **Testing**: Unit and integration tests, including Testcontainers support.
- **Docker**: Docker and AWS Elastic Beanstalk configurations.

## Prerequisites

- Java 17 or later
- Maven 3.6.3 or later
- Docker (optional, if you plan to use containerization)

## Getting Started

1. Clone the repository:
    ```bash
    git clone https://github.com/ArjanaaTernava/amigoscodecourse-backend.git
    ```
2. Navigate to the project directory:
    ```bash
    cd amigoscodecourse-backend
    ```
3. Build the project with Maven:
    ```bash
    mvn clean install
    ```
4. Run the application:
    ```bash
    mvn spring-boot:run
    ```

## Running with Docker

1. Build and start the container:
    ```bash
    docker-compose up --build
    ```
2. Access the application at `http://localhost:3000/`.

## Testing

- Run unit and integration tests using Maven:
    ```bash
    mvn test
    ```

## Frontend Repository

The application UI is built with React. You can find the repository [here](https://github.com/ArjanaaTernava/amigoscodecourse-frontend).

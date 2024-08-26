# AmigosCode Course Backend Application

This repository contains a Java-based application developed as part of the [Full-Stack Professional course](https://www.amigoscode.com/courses/full-stack-professional) by AmigosCode. It serves as a learning project, demonstrating various backend concepts in Java, Spring Boot, AWS and Docker.


## Main Backend Topics Learned

Here are the key backend concepts I learned and applied in this project:

- **Spring Boot 3**
- **HTTP & API Development**
- **Robust Error Handling Techniques**
- **Databases & PostgreSQL**
- **Spring Data JPA**
- **Flyway for Seamless Database Migrations**
- **JDBC for Efficient Database Communication**
- **Testing Strategies for Robust, Reliable Code**
- **Docker for Containerization and Deployment**
- **AWS for Cloud-Based Hosting and Scaling**
- **DevOps Best Practices**

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
    git clone https://github.com/ArjanaaTernava/FullStackProfessional-Backend.git
    ```
2. Navigate to the project directory:
    ```bash
    cd FullStackProfessional-Backend
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

The application UI is built with React. You can find the repository [here](https://github.com/ArjanaaTernava/FullStackProfessional-Frontend).

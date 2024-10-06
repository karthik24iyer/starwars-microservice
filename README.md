# Star Wars Microservice

A comprehensive microservice providing detailed information about various entities in the Star Wars universe, including planets, spaceships, vehicles, people, films, and species.
A Sequence diagram is also attached in the repository for a quick overview of Design Pattern & code flow.

## Table of Contents
1. [Features](#features)
2. [Technologies Used](#technologies-used)
3. [Project Structure](#project-structure)
4. [Getting Started](#getting-started)
5. [API Documentation](#api-documentation)
6. [Endpoints](#endpoints)
7. [Security](#security)
8. [Caching and Offline Mode](#caching-and-offline-mode)
9. [Testing](#testing)
10. [Docker Deployment](#docker-deployment)
11. [CI/CD Pipeline](#cicd-pipeline)
12. [Design Patterns](#design-patterns)
13. [Error Handling](#error-handling)
14. [Logging](#logging)
15. [Performance Considerations](#performance-considerations)

## Features
- Fetch detailed information about Star Wars planets, spaceships, vehicles, people, films, and species
- Swagger API documentation for easy API exploration
- Caching mechanism for improved performance
- Offline mode support with toggle functionality
- API key authentication for secure access
- Comprehensive error handling and logging
- Docker support for easy deployment
- CI/CD pipeline using Jenkins
- Thorough unit and integration tests

## Technologies Used
- Java 11
- Spring Boot 2.5.x
- Spring Security
- Maven
- JUnit 5
- Mockito
- Swagger / OpenAPI 3.0
- Docker
- Jenkins
- Lombok
- SLF4J & Logback

## Project Structure

src
├── main
│   ├── java
│   │   └── com
│   │       └── starwars
│   │           ├── config
│   │           ├── controller
│   │           ├── model
│   │           ├── service
│   │           ├── repository
│   │           ├── exception
│   │           ├── security
│   │           └── util
│   └── resources
│       ├── application.properties
│       └── logback-spring.xml
├── test
│   └── java
│       └── com
│           └── starwars
│               ├── controller
│               ├── service
│               └── integration
├── Jenkinsfile
└── Dockerfile

## Getting Started
1. Clone the repository: git clone https://github.com/yourusername/starwars-microservice.git
2. Build the project: ./mvnw clean install
3. Run the application: ./mvnw spring-boot:run

## API Documentation
Swagger UI is available at `http://localhost:8080/swagger-ui.html` when the application is running. This provides an interactive interface to explore and test the API endpoints.

## Endpoints

- GET /api/starwars/planets/{name}
- GET /api/starwars/spaceships/{name}
- GET /api/starwars/vehicles/{name}
- GET /api/starwars/people/{name}
- GET /api/starwars/films/{name}
- GET /api/starwars/species/{name}

Detailed request/response schemas are available in the Swagger documentation.

## Security
- API key authentication implemented using a custom interceptor
- Input validation and sanitization using Hibernate Validator
- Security headers (XSS protection, HSTS, etc.) configured in SecurityConfig
- HTTPS enforcement in production environments

## Caching and Offline Mode
- In-memory caching implemented using Spring's @Cacheable annotation
- Offline mode toggle available through application properties
- Offline data is served when the toggle is on or when the external API is unavailable

## Testing
- Unit tests for all service and controller classes
- Integration tests for end-to-end functionality
- Mock MVC tests for controller layer

Run the tests using: ./mvnw test

## Docker Deployment
1. Build the Docker image: docker build -t starwars-service .
2. docker run -p 8080:8080 starwars-service
(A `docker-compose.yml` file is also provided for easy deployment with environment variables.)

## CI/CD Pipeline
A Jenkinsfile is included in the repository, configuring a pipeline that:
1. Builds the application
2. Runs tests
3. Builds a Docker image
4. Pushes the image to a Docker registry
5. Deploys to a development environment

Ensure Jenkins is set up with necessary plugins and credentials before using the pipeline.

## Design Patterns
1. Dependency Injection: Used via Spring's @Autowired annotation
2. Singleton: Default scope for Spring beans
3. Factory: Utilized through Spring's BeanFactory
4. Proxy: Implemented for caching mechanism
5. MVC: Overall application structure
6. DTO: Used for API responses
7. Repository: Abstraction for data access
8. Builder: Implemented using Lombok's @Builder annotation

## Error Handling
- Global exception handling using @ControllerAdvice
- Custom exceptions for business logic errors
- Consistent error response structure across the API

## Logging
- SLF4J with Logback for flexible logging
- Different log levels for development and production environments
- Logged information includes request details, processing time, and error stack traces

## Performance Considerations
- Caching mechanism to reduce load on the external Star Wars API
- Connection pooling for efficient resource utilization
- Potential for future implementation of pagination for large data sets
# Using an official OpenJDK runtime as a parent image
FROM openjdk:11-jre-slim

WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/app.jar"]
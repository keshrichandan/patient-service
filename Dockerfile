# Use the official Maven image to build the project
FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /app

# Copy the project files and build the application
COPY . .
RUN mvn clean package

# Use a lightweight Java runtime to run the application
FROM openjdk:21-jdk
WORKDIR /app

# Copy the built JAR from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Expose the application port
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]

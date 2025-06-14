# Use official Maven image with OpenJDK 21 for building
FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /app

# Copy project files and build the application
COPY . .
RUN mvn clean package -DskipTests

# Use a lightweight Java runtime for running the application
FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app

# Copy the built JAR from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Expose the application port
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
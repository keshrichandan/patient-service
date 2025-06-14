# Use official OpenJDK 21 as the base image
FROM openjdk:21-jdk

# Set working directory
WORKDIR /app

# Install Maven
RUN yum install -y maven

# Verify installation
RUN mvn -version

# Copy JAR file from the target directory (assuming Maven or Gradle is used)
COPY target/patient-service-0.0.1-SNAPSHOT.jar app.jar

# Buid project
RUN mvn clean install

# Expose the application port (change if needed)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
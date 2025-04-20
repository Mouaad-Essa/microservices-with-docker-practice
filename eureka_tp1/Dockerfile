# Use an official OpenJDK 17 as a base image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the eureka-server jar file into the container
COPY target/eureka_tp1-0.0.1-SNAPSHOT.jar /app/eureka-server.jar

# Expose port 8761, which Eureka uses by default
EXPOSE 8761

# Command to run the Eureka server
ENTRYPOINT ["java", "-jar", "eureka-server.jar"]

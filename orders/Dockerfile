FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/orders-1.0-SNAPSHOT.jar orders.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "orders.jar"]

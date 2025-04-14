FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY target/usermicroservice-0.0.1-SNAPSHOT.jar usermicroservice.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "usermicroservice.jar"]
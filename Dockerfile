FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY target/Cartmicroservice-0.0.1-SNAPSHOT.jar Cartmicroservice.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "Cartmicroservice.jar"]
FROM eclipse-temurin:17-jdk-alpine
LABEL authors="lvilelas"
WORKDIR /app
COPY chavepix/target/chavepix-0.0.1-SNAPSHOT.jar chavepix-0.0.1-SNAPSHOT.jar
COPY docker-compose.yaml docker-compose.yaml
EXPOSE 8080

ENTRYPOINT ["java","-Dspring.profiles.active=prod","-jar","chavepix-0.0.1-SNAPSHOT.jar"]
FROM eclipse-temurin:17-jdk-alpine
LABEL authors="lvilelas"
WORKDIR /app
COPY chavepix/target/chavepix-0.0.1-SNAPSHOT.jar chavepix-0.0.1-SNAPSHOT.jar
VOLUME /tmp
EXPOSE 8080

ENTRYPOINT ["java","-jar","chavepix-0.0.1-SNAPSHOT.jar"]
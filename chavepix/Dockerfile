FROM amazoncorretto:22-alpine-jdk
LABEL authors="lvile"
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/chavepix-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
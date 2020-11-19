FROM openjdk:8-alpine
COPY build/libs/rest-service-0.0.1-SNAPSHOT.jar /usr/local/app/
WORKDIR /usr/local/app/
ENTRYPOINT ["java", "-jar", "rest-service-0.0.1-SNAPSHOT.jar"]

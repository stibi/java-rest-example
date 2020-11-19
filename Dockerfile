FROM vutreras/openjdk-8-alpine-with-gradle:latest AS build

RUN mkdir -p /app/src
WORKDIR /app/src
COPY . /app/src
RUN ./gradlew build

FROM openjdk:8-alpine

COPY --from=build /app/src/build/libs/rest-service-0.0.1-SNAPSHOT.jar /usr/local/app/
WORKDIR /usr/local/app/
ENTRYPOINT ["java", "-jar", "rest-service-0.0.1-SNAPSHOT.jar"]

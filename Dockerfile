FROM openjdk:8-alpine AS build
RUN mkdir -p /app/src
WORKDIR /app/src
COPY . /app/src
RUN ./gradlew build

FROM openjdk:8-alpine
COPY --from=build /app/src/build/libs/rest-service-0.0.1-SNAPSHOT.jar /usr/local/app/
WORKDIR /usr/local/app/
ENV MOJE_TEST_ENV=defaultnihodnota
ENTRYPOINT ["java", "-jar", "rest-service-0.0.1-SNAPSHOT.jar"]

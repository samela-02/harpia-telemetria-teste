FROM eclipse-temurin:21.0.7_6-jdk-alpine-3.21 AS build
WORKDIR /app
COPY . /app
RUN chmod +x ./mvnw
RUN ./mvnw clean package

FROM eclipse-temurin:21.0.7_6-jre-alpine-3.21
WORKDIR /app
COPY --from=build /app/target/harpia-ms-telemetria-0.0.1-SNAPSHOT.jar /app
ENTRYPOINT ["java", "-jar", "harpia-ms-telemetria-0.0.1-SNAPSHOT.jar"]
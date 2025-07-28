FROM eclipse-temurin:21.0.7_6-jdk-noble AS build
WORKDIR /app
COPY . /app
RUN chmod +x ./mvnw
RUN ./mvnw clean package

FROM eclipse-temurin:21.0.7_6-jre-noble
WORKDIR /app
RUN mkdir /app/certs
COPY ./certs /app/certs
COPY --from=build /app/target/harpia-ms-telemetria-0.0.1-SNAPSHOT.jar /app
ENTRYPOINT ["java", "-jar", "harpia-ms-telemetria-0.0.1-SNAPSHOT.jar"]

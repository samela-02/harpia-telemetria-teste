# ------------ Stage 1: Build -------------
# Esta parte compila seu código Java em um arquivo .jar
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Otimização de cache do Maven
COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN ./mvnw dependency:go-offline -DskipTests

# Compila o projeto
COPY src src
RUN ./mvnw clean package -DskipTests

# ------------ Stage 2: Runtime -------------
# Esta parte cria a imagem final e leve que será executada
FROM eclipse-temurin:21-jre-noble
WORKDIR /app

# APENAS CRIA O DIRETÓRIO. OS CERTIFICADOS SERÃO INJETADOS DEPOIS PELO PIPELINE.
RUN mkdir -p /app/certs

# Copia o .jar da etapa de build
COPY --from=build /app/target/harpia-ms-telemetria-0.0.1-SNAPSHOT.jar app.jar

# Define como a aplicação será iniciada
ENTRYPOINT ["java", "-jar", "app.jar"]

FROM eclipse-temurin:21-jdk-jammy as builder

WORKDIR /opt/app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
COPY ./src ./src
RUN ls -lah
RUN ./mvnw clean install -DskipTests=true


FROM eclipse-temurin:21-jdk-jammy as builder

WORKDIR /opt/app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
COPY ./src ./src
RUN chmod +x mvnw
RUN ./mvnw clean install -DskipTests=true


FROM eclipse-temurin:21-jdk-jammy as builder

WORKDIR /opt/app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
COPY ./src ./src
RUN ./mvnw clean install -DskipTests=true

FROM eclipse-temurin:21-jre-ubi9-minimal
WORKDIR /opt/app

RUN groupadd -g 10000 javagroup
RUN useradd -r -s /sbin/nologin -u 10000 -G javagroup javauser
RUN chown -R 10000:10000 /opt/app/
USER 10000
COPY --from=builder /opt/app/target/*.jar /opt/app/magma.jar

ENTRYPOINT ["java", "-jar",  "/opt/app/magma.jar"]

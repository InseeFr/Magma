FROM eclipse-temurin:21-jdk-jammy AS builder
WORKDIR /opt/app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
COPY ./impl ./impl
COPY ./interface ./interface
COPY ./oas ./oas
RUN chmod +x mvnw
RUN ./mvnw clean install -DskipTests=true

FROM eclipse-temurin:21-jre-jammy

RUN groupadd -g 10000 javagroup
RUN useradd -u 10000 -g javagroup -s /usr/sbin/nologin javauser
RUN mkdir /opt/app/
RUN chown -R 10000:10000 /opt/app/

USER 10000
COPY --from=builder /opt/app/target/*.jar /opt/app/magma.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar",  "/opt/app/magma.jar"]
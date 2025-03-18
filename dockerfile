FROM eclipse-temurin:21-jre-ubi9-minimal
WORKDIR /application

RUN addgroup -g 10000 javagroup
RUN adduser -D -s / -u 10000 javauser -G javagroup
RUN chown -R 10000:10000 /application

USER 10000
COPY target/*.jar magma.jar
ENTRYPOINT ["java", "-jar",  "/application/magma.jar"]

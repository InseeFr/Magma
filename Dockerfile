FROM eclipse-temurin:21-jre-ubi9-minimal
WORKDIR /application

RUN groupadd -g 10000 javagroup
RUN useradd -r -s /sbin/nologin -u 10000 -G javagroup javauser
RUN chown -R 10000:10000 /application

USER 10000
COPY target/*.jar magma.jar
RUN chown -R javauser:javagroup /application

ENTRYPOINT ["java", "-jar",  "/application/magma.jar"]

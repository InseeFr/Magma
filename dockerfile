FROM maven:3.9.6 as mvn
WORKDIR /magma
COPY ./ /magma/
RUN mvn -B -f /magma/pom.xml package

FROM tomcat:9.0-jre17
COPY --from=mvn magma/target/*.war /usr/local/tomcat/webapps/
CMD ["catalina.sh", "run"]

FROM maven:3.6.2 as mvn
WORKDIR /magma
COPY ./ /magma/
RUN mvn -B -f /magma/pom.xml package

FROM tomcat:9.0-jre17
COPY --from=mvn magma/target/magma.war /usr/local/tomcat/webapps/
#ADD ./config/start.sh /usr/local/tomcat
#RUN chmod +x /usr/local/tomcat/start.sh
#ENTRYPOINT [ "/usr/local/tomcat/start.sh"]
CMD ["catalina.sh", "run"]

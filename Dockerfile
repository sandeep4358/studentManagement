FROM tomcat:10.1.16-jdk17-temurin-jammy

MAINTAINER "SANDEEP KUMAR"

#Transfer our WAR

CMD ["catalina.sh", "run"]
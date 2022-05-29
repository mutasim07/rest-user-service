FROM openjdk:8-jdk-alpine
MAINTAINER Mutasim
COPY target/user-rest-service-0.0.1-SNAPSHOT.jar user-rest-service-1.0.0.jar
ENTRYPOINT ["java","-jar","/user-rest-service-1.0.0.jar"]
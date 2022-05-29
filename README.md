# User Service


## Overview

This project has been implemented as an assignment by Mutasim Jillani. It exposes a user service that can store user and contact data:

```
https://github.com/mutasim07/rest-user-service.git
```

The project design and details of API have been described in the subsequent sections

### How to run

The project is a spring boot application, and can be run as such. H2 database is used to store user and contact data.

H2 console has been enabled and can be opened on the following URL after launching the application:

```http request
http://localhost:8080/h2-ui/login.jsp
```

The following credentials (as described in application.properties) can be used to log into the console:
```properties
Driver-Class: org.h2.Driver
JDBC-URL: jdbc:h2:mem:testdb
User-Name: sa
Password:
```

### Design Considerations

#### Database
The application uses H2 in-memory database, which was chosen solely due to the brevity of time. DB contains the following tables:

user (User_ID, FirstName, LastName)<br />
user_email (Email_ID, User_ID, Email)<br />
user_phone (Phone_ID, User_ID, Phone)

#### Architecture

1. This is a spring boot application and uses Spring JPA repositories for database operations.
2. Spring boot doc was used to implement swagger in this service
3. Application is divided into three main layers: Controller, Service, Repository
4. Since there was no such requirement, and due to brevity of time, no security is implemnented on API level
5. Unit tests have been written for service layer using JUnit and mockito.

## API Interfaces

Run the application and head over to below url for all apis and their details
http://localhost:8080/swagger-ui/index.html

## Docker

This service is dockerized. Dockerfile is placed at the root folder of this service.
Here are the commands to create docker image and then run this service in a docker container
Inside the root folder, run the following commands:

$mvn clean package

$java -jar target/user-rest-service-0.0.1-SNAPSHOT.jar

$docker build --tag=user-rest-service:latest .

$docker run -p8887:8080 user-rest-service:latest

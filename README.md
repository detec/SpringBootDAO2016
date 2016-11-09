# Spring Boot DAO 2016 #

This is a template project to build with Maven for DAO layer and REST service. Its core components are:

- Hibernate 5.2;
- Spring Boot 1.4;
- Jackson Databind 2.8 with JSR310 support;
- sl4j/Logback;
- MySQL 5;
- Java 8.

DAO layer is built with JPA 2 Entity Manager to provide loose coupling with Hibernate 5. DAO and service layers are implemented as generic as possible to avoid excess implementation classes for every entity. DAO layer provides additional methods with JPA 2 Criteria API to construct fine-grained collection queries. If your business logic is simple you can avoid creating specific service implementations of GenericService interface and inject it directly into REST controller classes.

Sample rest controller supports exception handling and JSON pretty printing. Change src/main/resources/datasource.properties to meet your project requirements.
To test this template run this project as a compiled JAR and call endpoint GET http://localhost:8080/test . It should return 'true' in response body. You can also use additional endpoint to check current server time: GET http://localhost:8080/now . Requests should be accompanied by "Accept" and "Content-Type" headers with value application/json.  
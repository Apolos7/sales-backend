# Sales Back-end

Sales Backend is an API built in order to put into practice concepts learned during the course "Java COMPLETO Programação Orientada a Objetos +Projetos", in addition, with the motivation of further enriching the project, spring security was added and it was done the documentation via OpenAPI.

## 🍃 Spring Boot

Spring Boot is a quick way to start a complex java application, by providing simple ways to configure your project. Spring Boot is just one part of the spring ecosystem, various others springs projects make possible for building java applications. These are some dependencies that are being used in this project:

* Spring Web
* Spring Security
* Spring Boot DevTools
* Spring Data JPA
* H2 Datanase
* PostgreSQL Driver
* Java-JWT

## 🛑 Requirements

- [x] Java JDK 17
- [x] IDE (VIsual Studio Code, Eclipse, IntelliJ, Spring Tool Suite)
- [x] Recommended having a tool for testing the API (Postman, Insomnia)
- [x] Recommended having a DBMS (Data Base Management System) or use H2 Database for test purpose only

## ⚠️ How to run this project

After downloading the project, you will first need to set `JWTSECRET` as an environment variable, after that you can run `mvn clean compile` to download and compile the dependencies and then select which profile you want to use (this setting is present in  `application.properties`), after these simple steps you can run the application.

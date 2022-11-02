# Getting Started

There is a major new technology that is destined to be a disruptive force in the field of
transportation: the drone. Just as the mobile phone allowed developing countries to leapfrog
older technologies for personal communication, the drone has the potential to leapfrog traditional
transportation infrastructure.

## The service should allow:

* registering a drone;
* loading a drone with medication items;
* checking loaded medication items for a given drone;
* checking available drones for loading;
* check drone battery level for a given drone;

## Application data

Medications data is added in run time so not need to add it via api
Mll medication code (ABCDE0000, ABCDE1111,... ABCDE9999)
Application logs drone states in table ```drone_audit```
Drone application images based on openjdk:8-jre-alpine3.9 to be more lite 

## Technologies

* Java 8
* Spring Boot
* Hibernate
* Mysql

## Tools

* Operating System (Linux => Manjaro) Optional anyone good\
* Postman and Jmeter(more dynamism specifically with concurrency)
* Intellij Idea
* Docker and Docker Compose
* Maven
* Phpmyadmin or Dbeaver-ce (Recommended)
* Git and GitHub

## How to run the application

* Editor
    * open application in intellij or any editor
    * switch between commented and uncommented property ```spring.datasource.url``` in application.properties
    * run application
    * use this api to sure that application is healthy ```http://localhost:8080/api/drone/ping```
* Package application to war file and run it
    * maven clean package
    * run mysql database on port 3306
    * java -jar drone.war
* Containerization (Recommended)
    * Docker must be pre installed and running
    * in project root path that contains src folder run command ```docker-compose up --build``` this will build drone
      application image and run it with mysql and phpmyadmin

## Application APIs

* application base api ```http://localhost:8080/api/drone/```
* use swagger to navigate all APIs [swagger-ui](http://localhost:8080/swagger-ui/index.html#/)
* use postman collection in project to execute any API
* database url [drone database] (localhost:80) username:root, password:musala

### Reference Documentation

* [stackoverflow](https://stackoverflow.com)
* [baeldung.com](https://www.baeldung.com)

### Some Notes

* Mysql database port is 3306 and drone application port 8080
* We ignore concurrency problems like ```@Version``` ```OptimisticLockException``` and ```StaleStateException``` for
  more simplicity
* We ignore test cases due to lack of time
* Maybe there is some cases(code) that is not perfect this because of lack of more documentation

## Hopes

* Please give code more caring and review
* There more and solid spring knowledge to be approved, but I wait the opportunity
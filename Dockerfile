FROM openjdk:latest
COPY target/drone.war drone.war
EXPOSE 8080
ENTRYPOINT ["java","-jar","drone.war"]
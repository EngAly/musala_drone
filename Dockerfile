FROM openjdk:8-jre-alpine3.9
COPY target/drone.war drone.war
EXPOSE 8080
ENTRYPOINT ["java","-jar","drone.war"]
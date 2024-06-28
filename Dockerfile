FROM adoptopenjdk/openjdk11:latest

EXPOSE 8080 8081

RUN mkdir app

COPY target/TaskPlanner-0.0.0.jar app
COPY src/main/resources/config.yml app

WORKDIR app

CMD ["java","-jar","TaskPlanner-0.0.0.jar","server","config.yml"]
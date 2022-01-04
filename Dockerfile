FROM openjdk:17-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} pizza-service-app.jar
ENTRYPOINT ["java","-jar","/pizza-service-app.jar"]
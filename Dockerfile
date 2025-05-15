FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/gestion-teatro-1.0.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
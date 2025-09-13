FROM openjdk:17

COPY target/*.jar app.jar

EXPOSE 8989

ENTRYPOINT ["java", "-jar", "/app.jar"]

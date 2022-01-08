FROM openjdk:11
EXPOSE 8082
COPY target/*.jar kfservice.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=production", "/kfservice.jar"]
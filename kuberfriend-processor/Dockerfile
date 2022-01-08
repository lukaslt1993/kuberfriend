FROM openjdk:11
EXPOSE 8082
COPY target/*.jar kuberfriend-processor.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=production", "/kuberfriend-processor.jar"]
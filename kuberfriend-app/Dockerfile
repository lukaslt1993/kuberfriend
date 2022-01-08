FROM openjdk:11
EXPOSE 8081
COPY target/*.jar kuberfriend-app.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=production", "/kuberfriend-app.jar"]
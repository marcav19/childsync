FROM amazoncorretto:21-alpine-jdk

COPY /spring/target/spring-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8000

ENTRYPOINT [ "java", "-jar", "/app.jar" ]
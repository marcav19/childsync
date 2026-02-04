FROM maven AS builder
COPY spring/pom.xml .
RUN mvn -B dependency:go-offline

COPY spring/src ./src
RUN mvn package -DskipTests

FROM amazoncorretto:21-alpine-jdk
COPY --from=builder target/spring-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "app.jar" ]
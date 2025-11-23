FROM maven:3.9.8-eclipse-temurin-21 as build

COPY . /app
WORKDIR /app
RUN mvn clean install -DskipTests


FROM amazoncorretto:21-alpine-jdk
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]

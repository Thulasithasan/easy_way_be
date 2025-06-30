# Stage 1: Build with Maven and Java 21
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build_image

WORKDIR /usr/src/easyway

COPY pom.xml ./
COPY src ./src

# Skip tests and formatting validation
RUN mvn clean package -DskipTests -Dspring-javaformat.skip=true

# Stage 2: Run with Java 21
FROM eclipse-temurin:21-alpine

WORKDIR /app

COPY --from=build_image /usr/src/easyway/target/easyway-0.0.1.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]

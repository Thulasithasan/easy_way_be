# Stage 1: Build with Maven
FROM maven:3.9.5-eclipse-temurin-17-alpine AS BUILD_IMAGE

WORKDIR /usr/src/easyway

COPY pom.xml ./
COPY src ./src

RUN mvn clean package -DskipTests

# Stage 2: Run with JRE
FROM eclipse-temurin:17-alpine

WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=BUILD_IMAGE /usr/src/easyway/target/easyway-0.0.1.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]

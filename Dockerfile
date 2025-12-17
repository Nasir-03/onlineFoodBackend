# ---------- Build stage ----------
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app

# Copy Maven build files
COPY online-food/pom.xml .
COPY online-food/src ./src

# Build Spring Boot JAR (skip tests for speed)
RUN mvn clean package -DskipTests

# ---------- Run stage ----------
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app

# Copy built JAR (wildcard handles SNAPSHOT jar names)
COPY --from=build /app/target/*.jar app.jar

# Documentation: Spring Boot normally runs on 8080
EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]

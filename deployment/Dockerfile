# Stage 1: Build with Gradle
FROM gradle:8.14-jdk21-alpine AS builder
WORKDIR /home/gradle/project
COPY . .
RUN gradle clean build -x test --no-daemon

# Stage 2: Create the runtime image
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

# Copy the executable jar from the build stage
COPY --from=builder /home/gradle/project/applications/app-service/build/libs/*.jar app.jar

# Expose the port the application runs on
EXPOSE 8000

ENV AWS_DYNAMODB_ENDPOINT=http://dynamodb-local:8000
ENV SPRING_RABBITMQ_HOST=rabbitmq

ENTRYPOINT ["java", "-jar", "app.jar"]

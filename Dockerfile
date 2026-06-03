FROM gradle:8.6-jdk17 AS builder
WORKDIR /home/gradle/project

# Copy project and build fat jar
COPY --chown=gradle:gradle . .
RUN gradle bootJar --no-daemon -x test

FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
ARG JAR_FILE=build/libs/*.jar
COPY --from=builder /home/gradle/project/${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]

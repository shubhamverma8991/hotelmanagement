# Stage 1: Build the application
FROM maven:3.8.5-openjdk-17 AS build

# Copy the project files into the container
COPY . .

# Build the application package, skipping tests
RUN mvn clean package -DskipTests

# Stage 2: Create the runtime image
FROM openjdk:17.0.1-jdk-slim

# Copy the JAR file from the build stage
COPY --from=build /target/mentormate-server-0.0.1-SNAPSHOT.jar demo.jar

# Expose port 8080 for the application
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "demo.jar"]

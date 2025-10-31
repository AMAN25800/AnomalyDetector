# Step 1: Use an official OpenJDK base image
FROM openjdk:17-jdk-slim AS builder

# Step 2: Set the working directory
WORKDIR /app

# Step 3: Copy the Maven or Gradle build files
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Step 4: Download dependencies (cached if unchanged)
RUN ./mvnw dependency:go-offline -B

# Step 5: Copy source code
COPY src ./src

# Step 6: Build the Spring Boot app
RUN ./mvnw clean package -DskipTests

# Step 7: Use a smaller image for the runtime
FROM openjdk:17-jdk-slim

# Step 8: Set the working directory
WORKDIR /app

# Step 9: Copy the JAR from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Step 10: Expose the backend port
EXPOSE 8081

# Step 11: Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

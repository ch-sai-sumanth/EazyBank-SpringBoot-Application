# Use an official OpenJDK runtime as a parent image
FROM eclipse-temurin:21-jdk

# Set the working directory
WORKDIR /app

# Copy Maven wrapper files and pom.xml to leverage Docker cache
COPY mvnw pom.xml ./
COPY .mvn .mvn

# Copy source code
COPY src ./src

# Build the application inside the container
RUN ./mvnw package -DskipTests

# Expose the application port
EXPOSE 8080

ENV SWAGGER_PORT=8080

# Run the application
CMD ["java", "-jar", "target/easybank-0.0.1-SNAPSHOT.jar"]

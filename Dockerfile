# Use the official OpenJDK base image
FROM openjdk:17-jdk

# Set the working directory inside the image
WORKDIR /app

# Copy the JAR file into the image
COPY /target/maybePad-0.0.1-SNAPSHOT.jar /app/maybePad-0.0.1-SNAPSHOT.jar

# Set the startup command to execute your application
CMD ["java", "-jar", "/app/maybePad-0.0.1-SNAPSHOT.jar"]
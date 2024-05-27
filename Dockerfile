FROM openjdk:17-ea-16-jdk
ARG JAR_FILE=build/libs/*jar
COPY ./build/libs/com.numbers-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]

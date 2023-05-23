FROM gradle:7-jdk19-focal
COPY . /app
WORKDIR /app
RUN ./gradlew bootJar
CMD ["java", "-jar", "./build/libs/notificationservice-0.0.1-SNAPSHOT.jar"]
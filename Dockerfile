FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} excercise.jar
ENTRYPOINT ["java","-jar","/excercise.jar"]
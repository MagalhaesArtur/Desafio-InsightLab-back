#FROM ubuntu:latest AS build
#
#RUN apt-get update
#RUN apt-get install openjdk-17-jdk -y
#COPY . .
#
#RUN apt-get install maven -y
#RUN mvn clean install
#
#FROM openjdk:17-jdk-slim
#
#EXPOSE 8080
#
#COPY --from=build /target/deploy_render-1.0.0.jar app.jar
#
#ENTRYPOINT [ "java", "-jar", "app.jar" ]

FROM maven:3.8.6-amazoncorretto-17 as build
WORKDIR /app
COPY . .
RUN mvn clean package -X -DskipTests

FROM openjdk:17-ea-10-jdk-slim
WORKDIR /app
COPY --from=build ./app/target/*.jar ./insight.jar
ENTRYPOINT java -jar insight.jar
FROM openjdk:17-jdk-alpine
EXPOSE 8989,3306
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} blog_app-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/blog_app-0.0.1-SNAPSHOT.jar"]
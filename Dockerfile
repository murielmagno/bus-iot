FROM adoptopenjdk/openjdk11:alpine-jre
ENV TZ=America/Sao_Paulo
#ENV JAVA_VERSION jdk-11.0.11+9
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Xmx512m","-Dserver.port=${PORT}","-jar","/app.jar"]




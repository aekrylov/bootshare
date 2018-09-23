FROM openjdk:8-jre-alpine

ENTRYPOINT ["/usr/bin/java", "-jar", "/app.jar"]

VOLUME ["/var/lib/bootshare"]

EXPOSE 80

ARG JAR_FILE
ADD target/${JAR_FILE} /app.jar

ENV spring.profiles.active=docker

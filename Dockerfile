FROM openjdk:8-jdk-alpine

VOLUME /tmp

COPY  chat-service-app-8002/target/chat-service-app-8002-2.0.jar app.jar

ENV SPRING_PROFILES_ACTIVE=docker \
    DUBBO_IP_TO_REGISTRY=182.92.120.192

ENTRYPOINT ["java","-jar","/app.jar"]
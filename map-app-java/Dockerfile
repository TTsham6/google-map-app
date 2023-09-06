FROM gradle:8-jdk17-alpine as builder
WORKDIR /home/gradle
COPY --chown=gradle:gradle build.gradle build.gradle
COPY --chown=gradle:gradle settings.gradle settings.gradle
COPY --chown=gradle:gradle src src
RUN gradle build


FROM eclipse-temurin:17-jdk-alpine as runner

RUN addgroup -S -g 1000 app
RUN adduser -S -G app -u 1000 -s /bin/ash app
USER app
WORKDIR /home/app
COPY --from=builder --chown=app:app /home/gradle/build/libs/*.jar app.jar
# RUN map-app-java/bin/map-app-java
ENTRYPOINT ["java", "-jar", "app.jar"]


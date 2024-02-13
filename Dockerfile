FROM gradle:8.2.1-jdk17 AS TEMP_BUILD_IMAGE
ENV APP_HOME=/usr/exchangeoffice/
WORKDIR $APP_HOME
COPY build.gradle settings.gradle $APP_HOME
COPY gradle $APP_HOME/gradle
COPY --chown=gradle:gradle . /home/gradle/src
USER root
RUN chown -R gradle /home/gradle/src

RUN gradle build || return 0
COPY . .
##RUN gradle clean build

FROM amazoncorretto:17

ENV ARTIFACT_NAME=webhook-manager-0.0.1-SNAPSHOT.jar
ENV APP_HOME=usr/webhook-manager
WORKDIR $APP_HOME
COPY --from=TEMP_BUILD_IMAGE $APP_HOME/build/libs/$ARTIFACT_NAME .
EXPOSE 8027
ENTRYPOINT exec java -jar ${ARTIFACT_NAME}
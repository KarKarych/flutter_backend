FROM amazoncorretto:17-alpine-jdk AS builder
ENV APP_HOME=/usr/app
WORKDIR $APP_HOME
COPY build.gradle settings.gradle gradlew $APP_HOME
COPY gradle $APP_HOME/gradle
RUN ./gradlew build
COPY src $APP_HOME/src
RUN ./gradlew build

FROM amazoncorretto:17-alpine-jdk AS layer_builder
ENV ARTIFACT_NAME=flutter-backend-0.0.1-SNAPSHOT.jar
ENV APP_HOME=/usr/app
WORKDIR $APP_HOME
COPY --from=builder $APP_HOME/build/libs/$ARTIFACT_NAME $APP_HOME/app.jar
RUN java -Djarmode=layertools -jar app.jar extract

FROM amazoncorretto:17-alpine-jdk
ENV APP_HOME=/usr/app
WORKDIR $APP_HOME
COPY --from=layer_builder $APP_HOME/dependencies .
COPY --from=layer_builder $APP_HOME/snapshot-dependencies .
COPY --from=layer_builder $APP_HOME/spring-boot-loader .
COPY --from=layer_builder $APP_HOME/application .
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]



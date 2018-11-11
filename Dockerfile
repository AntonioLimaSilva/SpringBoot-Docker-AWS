FROM openjdk:8u181-jdk-alpine3.8

LABEL maintainer="luclimasilva23@gmail.com"

ENV LANG C.UTF-8

RUN apk add --update bash

ADD build/libs/*.jar /app/beerstore.jar

CMD java -jar /app/beerstore.jar $APP_OPTIONS
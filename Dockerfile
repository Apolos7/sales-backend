FROM amazoncorretto:17

RUN mkdir /app

COPY ./target/sales-api.jar ./app/sales-api.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/sales-api.jar"]
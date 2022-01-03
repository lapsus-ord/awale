FROM openjdk:11
COPY ./target/awale-backend-*.jar /app/app.jar
WORKDIR /app
#ENTRYPOINT ["tail", "-f", "/dev/null"]
CMD ["java", "-jar", "app.jar", "--server.port=80"]
EXPOSE 80

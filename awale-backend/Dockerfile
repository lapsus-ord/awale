FROM maven:3.8.4-jdk-11
COPY . /awale
WORKDIR /awale
RUN mvn dependency:resolve compile
CMD ["mvn", "spring-boot:run", "-f", "pom.xml"]
EXPOSE 63221

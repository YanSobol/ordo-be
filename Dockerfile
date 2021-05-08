FROM openjdk:8

RUN apt-get update && apt-get install -y maven
COPY . /project
RUN  cd /project && mvn clean package

ENTRYPOINT ["java", "-Dspring.profiles.active=production,-Dserver.port=${PORT}", "-jar","/project/target/orgo-0.0.1-SNAPSHOT.jar"]
FROM openjdk:8

RUN apt-get update && apt-get install -y maven
COPY . /project
RUN  cd /project && mvn clean package

CMD java -Dspring.profiles.active=production -Dserver.port=$PORT -Xmx300m -jar /project/target/orgo-0.0.1-SNAPSHOT.jar
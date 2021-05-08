#FROM openjdk:8
#
#RUN apt-get update && apt-get install -y maven
#COPY . /project
#RUN  cd /project && mvn clean package
#
##run the spring boot application
#ENV JAVA_OPTS="SPRING_PROFILES_ACTIVE=local PORT=8075  SPRING_DATASOURCE_URL=jdbc:mysql://user:password@db:3306/ordo?reconnect=true&&allowPublicKeyRetrieval=true&&useSSL=false"
#
#ENTRYPOINT ["java ${JAVA_OPTS}", "-jar","/project/target/orgo-0.0.1-SNAPSHOT.jar"]

########build stage########
FROM maven:3.5-jdk-8 as maven_build
WORKDIR /project

COPY pom.xml .
COPY src ./src

RUN --mount=type=cache,target=/root/.m2 mvn clean package  -Dmaven.test.skip

########run stage########
FROM java:8
WORKDIR /project

COPY --from=maven_build /project/target/*.jar .

#run the app
ENTRYPOINT ["java", "-Dspring.profiles.active=production,-Dserver.port=8075", "-jar", "orgo-0.0.1-SNAPSHOT.jar", "-v"]
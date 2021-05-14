#FROM openjdk:8
#
#RUN apt-get update && apt-get install -y maven
#COPY . /project
#RUN  cd /project && mvn package
#EXPOSE 8075 5005
#
#ENTRYPOINT ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=5005", "-jar", "/project/target/orgo-0.0.1-SNAPSHOT.jar"]
#
#


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
ENV JAVA_OPTS "-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=5005"
CMD [ "bash", "-c", "java ${JAVA_OPTS} -jar *.jar -v"]
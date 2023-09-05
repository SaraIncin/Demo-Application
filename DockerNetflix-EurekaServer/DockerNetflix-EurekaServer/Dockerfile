FROM openjdk:17-oracle
ADD target/DockerSpringNetflix-EurekaServer-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD microservicio-0.0.1-SNAPSHOT.jar app.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
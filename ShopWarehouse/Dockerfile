FROM openjdk:8
COPY target/aoshw2-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egdxfile:/dev/./urandom/","-jar","/app.jar"]
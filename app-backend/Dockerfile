# base docker image this is built on
FROM openjdk:17
# ADD SOURCEJAR DESTINATIONDOCKERIMAGE
ADD target/app-backend-0.0.1-SNAPSHOT.jar app-backend-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app-backend-0.0.1-SNAPSHOT.jar"]
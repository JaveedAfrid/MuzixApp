FROM openjdk:11-jre-slim
WORKDIR /usr/share/app
COPY ./target/favourites-services-0.0.1-SNAPSHOT.jar myapp.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","myapp.jar"]
FROM openjdk:11-jre-slim
WORKDIR /usr/share/app
COPY ./target/MuzixLogin-0.0.1-SNAPSHOT.jar myapp.jar
EXPOSE 8080
ENV MYSQL_DB=user_docker_db
ENV JWT_SECRET=secretkey
ENTRYPOINT ["java","-jar", "myapp.jar"]
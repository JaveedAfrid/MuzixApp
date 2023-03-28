FROM openjdk:11-jre-slim
WORKDIR /usr/share/app
COPY ./target/recommendationservice-0.0.1-SNAPSHOT.jar myapp.jar
EXPOSE 5001
ENV MYSQL_DB=recommendation_docker_db
ENV JWT_SECRET=secretkey
ENTRYPOINT ["java","-jar", "myapp.jar"]
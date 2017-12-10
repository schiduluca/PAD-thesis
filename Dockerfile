FROM frolvlad/alpine-oraclejdk8:slim
RUN mkdir -p /app/
ADD build/libs/thesis-0.0.1-SNAPSHOT.jar /app/thesis-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/app/thesis-0.0.1-SNAPSHOT.jar"]
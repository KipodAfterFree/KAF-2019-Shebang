FROM openjdk:11-slim-buster
EXPOSE 7294
COPY build/libs/Shebang-1.0-runnable.jar /app.jar
CMD exec java -jar /app.jar
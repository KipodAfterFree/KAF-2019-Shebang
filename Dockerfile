FROM openjdk:11-slim-buster
EXPOSE 7294
COPY out/Shebang.jar /app.jar
CMD exec java -jar /app.jar
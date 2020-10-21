FROM openjdk:11.0-jre

COPY primes-finder-webapp/target/primes-finder-webapp-*.jar /app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app.jar"]
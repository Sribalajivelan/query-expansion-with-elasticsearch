FROM openjdk:17-slim

WORKDIR app/

COPY target/query-expansion-with-elasticsearch-*.jar ./query-expansion-with-elasticsearch.jar

EXPOSE 8080

CMD ["java", "-jar", "query-expansion-with-elasticsearch.jar"]
#!/bin/sh
echo "server.port=80" >> src/main/resources/application.properties
echo "spring.datasource.url=$(echo $DB_URL)" >> src/main/resources/application.properties
echo "spring.datasource.username=$(echo $DB_ID)" >> src/main/resources/application.properties
echo "spring.datasource.password=$(echo $DB_PASS)" >> src/main/resources/application.properties
echo "spring.datasource.driverClassName=$(echo $DB_DRIVER)" >> src/main/resources/application.properties
echo "spring.jpa.database-platform=$(echo $DB_DIALECT)" >> src/main/resources/application.properties

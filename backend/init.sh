#!/bin/sh
echo "server.port=80" > src/main/resources/application.properties
echo "spring.jpa.generate-ddl=true" >> src/main/resources/application.properties
echo "spring.jpa.hibernate.ddl-auto=update" >> src/main/resources/application.properties
if [ $DB -eq h2 ]; then
  echo "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect" >> src/main/resources/application.properties
  echo "spring.datasource.driverClassName=org.h2.Driver" >> src/main/resources/application.properties
elif [ $DB -eq mysql ] && [ $DB -eq mariadb ]; then
  echo "spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect" >> src/main/resources/application.properties
  echo "spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver" >> src/main/resources/application.properties
elif [ $DB -eq postgresql ]; then
  echo "spring.jpa.database-platform=org.hibernate.dialect.PostgreDialect" >> src/main/resources/application.properties
  echo "spring.datasource.driverClassName=org.postgresql.Driver" >> src/main/resources/application.properties
elif [ $DB -eq oracle ]; then
  echo "spring.jpa.database-platform=org.hibernate.dialect.OracleDialect" >> src/main/resources/application.properties
  echo "spring.datasource.driverClassName=oracle.jdbc.OracleDriver" >> src/main/resources/application.properties
fi
echo "spring.datasource.url=$(echo $DB_URL)" >> src/main/resources/application.properties
echo "spring.datasource.username=$(echo $DB_ID)" >> src/main/resources/application.properties
echo "spring.datasource.password=$(echo $DB_PASS)" >> src/main/resources/application.properties

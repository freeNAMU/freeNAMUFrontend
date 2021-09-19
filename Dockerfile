FROM amazoncorretto:11

# Install dependencies
RUN curl --silent --location https://rpm.nodesource.com/setup_14.x | bash - && yum install -y nodejs
COPY frontend/package.json /usr/src/freeNAMU/frontend/package.json
COPY frontend/package-lock.json /usr/src/freeNAMU/frontend/package-lock.json
WORKDIR /usr/src/freeNAMU/frontend
RUN npm install -g npm && npm install
COPY backend/.mvn /usr/src/freeNAMU/backend/.mvn
COPY backend/mvnw /usr/src/freeNAMU/backend/mvnw
COPY backend/pom.xml /usr/src/freeNAMU/backend/pom.xml
WORKDIR /usr/src/freeNAMU/backend
RUN chmod +x mvnw && ./mvnw dependency:go-offline

# Setup default properties
EXPOSE 80
ENV DB_URL jdbc:h2:file:/var/freenamu/freenamu
ENV DB_ID admin
ENV DB_PASS admin
ENV DB_JDBC org.h2.Driver
ENV DB_DIALECT org.hibernate.dialect.H2Dialect

# Build
COPY frontend/public /usr/src/freeNAMU/frontend/public
COPY frontend/src /usr/src/freeNAMU/frontend/src
COPY frontend/babel.config.js /usr/src/freeNAMU/frontend/babel.config.js
COPY frontend/tsconfig.json /usr/src/freeNAMU/frontend/tsconfig.json
COPY backend/src /usr/src/freeNAMU/backend/src
COPY backend/init.sh /usr/src/freeNAMU/backend/init.sh
WORKDIR /usr/src/freeNAMU/frontend
RUN npm run build
RUN cp -r dist/* /usr/src/freeNAMU/backend/src/main/resources/static
WORKDIR /usr/src/freeNAMU/backend
RUN chmod +x init.sh && ./init.sh
RUN chmod +x mvnw && ./mvnw package

# Run
CMD java -jar target/*.jar

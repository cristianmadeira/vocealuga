#!/bin/bash

cd src/main/resources/
cp -a example.application.properties application.properties
cd /app/
echo 'Updating source list ...'
apt update -y 
apt install dos2unix
dos2unix mvnw
mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005"
#./mvnw spring-boot:run -Dspring-boot.run.jvmArguments="agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005"
#./mvnwDebug spring-boot:run
#./mvnw spring-boot:run -Ddwp:server=y,transport=dt_socket,address=5000,suspend=n


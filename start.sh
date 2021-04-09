#!/bin/bash

cd src/main/resources/
cp -a example.application.properties application.properties
cd /app/
echo 'Updating source list ...'
apt update -y 
apt install dos2unix
dos2unix mvnw
./mvnw spring-boot:run -Dagentlib:jdwp=transport=dt_socket,address=5000,server=y,suspend=n


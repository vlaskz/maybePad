#!/bin/bash

# Define o local do JAVA_HOME
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64/

# Incrementa a versão no application.properties
version_line=$(grep "application.version=" src/main/resources/application.properties)
version=$(echo $version_line | cut -d'=' -f2 | tr -d 'v')
new_version="v$(echo "$version + 0.1" | bc)"
sed -i "s/application.version=$version/application.version=$new_version/g" src/main/resources/application.properties

# Construção e deploy
mvn clean package -DskipTests
docker build -t vlaskz/maybepad .
docker push vlaskz/maybepad
fly deploy
fly logs -a vlaskz-maybepad
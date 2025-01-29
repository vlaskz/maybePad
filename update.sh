#!/bin/bash

# Define o local do JAVA_HOME
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64/

# Salva o nome da branch atual
current_branch=$(git branch --show-current)

# Muda para a branch master e garante que está no último commit
git checkout master
git pull

# Lê a versão atual
current_version=$(grep "application.version=" application.properties | cut -d'=' -f2 | sed 's/v//')

# Incrementa a versão
IFS='.' read -ra ADDR <<< "$current_version"
new_version="v${ADDR[0]}.$((${ADDR[1]} + 1))"

# Atualiza o application.properties
sed -i "s/$current_version/$new_version/" application.properties

# Commita a nova versão
git add application.properties
git commit -m "Bump version to $new_version"
git push

# Construção e deploy
mvn clean package -DskipTests
docker build -t vlaskz/maybepad .
docker push vlaskz/maybepad
fly deploy
fly logs -a vlaskz-maybepad

# Retorna para a branch que estava antes (opcional)
git checkout $current_branch

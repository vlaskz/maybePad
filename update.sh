export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64/
mvn clean package -DskipTests
docker build -t vlaskz/maybepad .
docker push vlaskz/maybepad
fly deploy
fly logs -a vlaskz-maybepad


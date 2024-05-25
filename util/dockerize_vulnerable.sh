cd vulnerable-code
./mvnw package
docker build -f src/main/docker/Dockerfile.jvm -t quarkus/vulnerable-code-jvm .
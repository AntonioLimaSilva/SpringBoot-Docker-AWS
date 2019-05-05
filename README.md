# BUILD

docker build -t luclimasilva23/beerstore:0.5 -f devops/Dockerfile .

# RUN

docker run -p 8080:8080 --rm --network beer-net -e APP_OPTIONS='--spring.datasource.url=jdbc:postgresql://postgres:5432/postgres?user=postgres&password=****' luclimasilva23/beerstore:0.5
# Etapa 1: Compilación nativa
FROM quay.io/quarkus/ubi-quarkus-mandrel-builder-image:23.1-java21 AS build
WORKDIR /workspace
COPY . .
RUN ./mvnw clean package -Pnative -DskipTests

# Etapa 2: Imagen final mínima
FROM quay.io/quarkus/quarkus-micro-image:2.0
WORKDIR /work/
COPY --from=build /workspace/target/*-runner /work/application
EXPOSE 8080
CMD ["./application"]
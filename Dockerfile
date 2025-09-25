# Etapa de construcción
FROM maven:3.8.3-openjdk-17 AS builder
LABEL maintainer="gabo2791"
LABEL description="BestBankAppContainer"

# Crear directorio de trabajo
WORKDIR /app

# Copiar archivos de configuración de Maven primero para aprovechar caché

COPY pom.xml .

# Descargar dependencias sin compilar (esto solo se reejecuta si cambia el pom.xml)
RUN mvn dependency:go-offline

# Ahora sí, copiar el código fuente
COPY src src

# Compilar el proyecto sin correr tests
RUN mvn clean package -DskipTests

# Etapa de ejecución
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

ARG CACHE_BREAKER=1

#Activar variable para el nombre del jar
ARG JAR_NAME=BestBankAppContainer.jar

# Copiar el JAR compilado desde el builder
COPY --from=builder /app/target/${JAR_NAME} app.jar

# Exponer el puerto de la app
EXPOSE 8085

# Ejecutar la aplicación Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]
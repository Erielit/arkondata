# Etapa 1: Compilar el proyecto
FROM maven:latest AS build
WORKDIR /app

# Copia los archivos de proyecto al contenedor y compila la aplicación
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Crear la imagen final de runtime
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copia el .jar construido en la etapa de compilación
COPY --from=build /app/target/*.jar app.jar

# Exponer el puerto en el que la aplicación se ejecutará
EXPOSE 8081

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]

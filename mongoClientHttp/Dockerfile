# Usar una imagen base de Java (OpenJDK en este ejemplo)
FROM amazoncorretto:17-alpine

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo JAR de tu aplicación Spring Boot
COPY target/*.jar app.jar

# Define la variable de entorno para MongoDB host
ENV SPRING_DATA_MONGODB_HOST=mongo-container

# Exponer el puerto en el que escucha tu aplicación (por defecto 8080)
EXPOSE 8080

# Comando para ejecutar tu aplicación
ENTRYPOINT ["java","-jar","app.jar"]
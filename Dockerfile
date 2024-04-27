# Use a imagem base do Java
FROM openjdk:11-jre-slim

# Copie o arquivo JAR da sua aplicação para o contêiner
COPY target/cooperativism-service-0.0.1-SNAPSHOT.jar /app/cooperativism-service-0.0.1-SNAPSHOT.jar

# Expõe a porta da aplicação
EXPOSE 8080

# Comando para iniciar a aplicação quando o contêiner for iniciado
CMD ["java", "-jar", "/app/cooperativism-service-0.0.1-SNAPSHOT.jar"]

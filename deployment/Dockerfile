#FROM eclipse-temurin:21-jdk-alpine
#VOLUME /tmp
#COPY *.jar FranchiseApi.jar
#ENV JAVA_OPTS=" -Xshareclasses:name=cacheapp,cacheDir=/cache,nonfatal -XX:+UseContainerSupport -XX:MaxRAMPercentage=70 -Djava.security.egd=file:/dev/./urandom"
## Replace with a non-root user to avoid running the container with excessive privileges
#USER appuser
#ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS  -jar FranchiseApi.jar" ]

# Imagen base con Java 21 y Alpine para menor peso
FROM eclipse-temurin:21-jdk-alpine

# Crear directorio temporal (Spring Boot lo usa para operaciones temporales)
VOLUME /tmp

# Copiar el .jar generado desde Gradle
COPY FranchiseApi.jar app.jar

# Variables de entorno para optimización JVM (puedes ajustarlas si es necesario)
ENV JAVA_OPTS="-XX:MaxRAMPercentage=70 -Djava.security.egd=file:/dev/./urandom"

# Ejecutar la app con opciones JVM
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]

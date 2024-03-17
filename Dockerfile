# Uporabite uradno OpenJDK sliko z željeno različico JDK
FROM openjdk:21

# Opcijsko: Set the ENV JAVA_OPTS to adjust JVM options
ENV JAVA_OPTS=""

# Nastavite delovni imenik v kontejnerju za aplikacijo
WORKDIR /app

# Kopirajte artefakte gradnje iz ciljnega direktorija vaše Quarkus aplikacije
# Opomba: Za pravilno kopiranje se prepričajte, da je struktura vašega projekta standardna
# in da uporabljate Maven za pakiranje aplikacije
COPY target/quarkus-app/lib/ /app/lib/
COPY target/quarkus-app/*.jar /app/
COPY target/quarkus-app/app/ /app/app/
COPY target/quarkus-app/quarkus/ /app/quarkus/

# Izpostavite port, na katerem bo aplikacija dostopna
EXPOSE 9001

# Zagon aplikacije s pomočjo Java naredi, z opcijami, ki so potrebne za Quarkus
CMD ["java", "-jar", "/app/quarkus-run.jar"]

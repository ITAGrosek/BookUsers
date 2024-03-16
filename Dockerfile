# Izberite osnovno sliko, ki ustreza različici Jave, ki jo potrebuje vaš projekt
FROM adoptopenjdk/openjdk21:jre-21.0.21_9-alpine

# Nastavite delovni imenik v kontejnerju
WORKDIR /app

# Kopirajte izgrajene artefakte iz ciljnega direktorija v delovni direktorij kontejnerja
COPY target/quarkus-app/lib/ /app/lib/
COPY target/quarkus-app/*.jar /app/
COPY target/quarkus-app/app/ /app/app/
COPY target/quarkus-app/quarkus/ /app/quarkus/

# Nastavite točko vstopa za kontejner
ENTRYPOINT ["java", "-jar", "/app/quarkus-run.jar"]

# Izpostavite port, ki ga vaša aplikacija uporablja
EXPOSE 8080

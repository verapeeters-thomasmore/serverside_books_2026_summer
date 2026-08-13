# Database Setup & Migratie (Neon PostgreSQL)

Dit document beschrijft hoe de applicatie gekoppeld wordt aan de Neon PostgreSQL cloud-database.

## 1. Neon Database Aanmaken
1. Ga naar [Neon.tech](https://neon.tech/) en log in of maak een gratis account aan.
2. Maak een nieuw project/database aan (kies de regio die het dichtst bij u ligt, bijv. Europe).
3. Kopieer de verbindinggegevens (de **Connection String** of de losse host, database, user en password parameters).

## 2. Lokale Configuratie
De database-instellingen worden lokaal beheerd via `src/main/resources/application-local.properties` om te voorkomen dat inloggegevens in Git worden ingediend.

Vervang de placeholders in `application-local.properties` met uw Neon-gegevens:

```properties
spring.datasource.url=jdbc:postgresql://<neon-hostname>/neondb?sslmode=require
spring.datasource.username=<neon-username>
spring.datasource.password=<neon-password>
spring.datasource.driver-class-name=org.postgresql.Driver
```

## 3. Eenmalige Schema & Data Initialisatie
Omdat we met een persistente database werken, hoeft de data uit `schema.sql` en `data.sql` slechts **één keer** geladen te worden.

1. **Eerste run (schoonmaken en opbouwen):** Zorg dat de volgende instellingen actief zijn in `application-local.properties`:
   ```properties
   spring.sql.init.mode=always
   spring.jpa.hibernate.ddl-auto=create
   ```
   *Dit zorgt ervoor dat bestaande tabellen worden verwijderd (drop) en alle tabellen inclusief data correct en schoon worden aangemaakt.*

2. **Volgende runs:** Nadat de applicatie succesvol is opgestart en de tabellen zijn gevuld, wijzigt u dit direct terug naar:
   ```properties
   spring.sql.init.mode=never
   spring.jpa.hibernate.ddl-auto=update
   ```
   *Dit voorkomt duplicate key errors, voorkomt dat data verloren gaat en verkort de opstarttijd bij herstarts.*

# Eventify

### Persistencia estratégica para una plataforma de gestión de eventos

<p align="center">
    <img src="https://img.shields.io/badge/Java-26-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java 26">
    <img src="https://img.shields.io/badge/Spring%20Boot-4.1.1-6DB33F?style=for-the-badge&logo=springboot&logoColor=white" alt="Spring Boot">
    <img src="https://img.shields.io/badge/Spring%20Data-JPA-6DB33F?style=for-the-badge&logo=spring&logoColor=white" alt="Spring Data JPA">
    <img src="https://img.shields.io/badge/Hibernate-ORM-59666C?style=for-the-badge&logo=hibernate&logoColor=white" alt="Hibernate">
    <img src="https://img.shields.io/badge/PostgreSQL-17-4169E1?style=for-the-badge&logo=postgresql&logoColor=white" alt="PostgreSQL">
    <img src="https://img.shields.io/badge/Docker-Containerized-2496ED?style=for-the-badge&logo=docker&logoColor=white" alt="Docker">
    <img src="https://img.shields.io/badge/Maven-Build-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white" alt="Maven">
    <img src="https://img.shields.io/badge/JUnit-5-25A162?style=for-the-badge&logo=junit5&logoColor=white" alt="JUnit 5">
    <img src="https://img.shields.io/badge/Mockito-Testing-78A641?style=for-the-badge" alt="Mockito">
    <img src="https://img.shields.io/badge/OpenAPI-Swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=black" alt="Swagger">
    <img src="https://img.shields.io/badge/ChatGPT-Generative%20AI-412991?style=for-the-badge&logo=openai&logoColor=white" alt="ChatGPT">
</p>

<p align="center">
    API REST desarrollada con Spring Boot que implementa persistencia real mediante
    Spring Data JPA, Hibernate y PostgreSQL, incorporando CRUD completo,
    validaciones, paginación, ordenamiento y pruebas de persistencia.
</p>

---

# Estado del proyecto

![Status](https://img.shields.io/badge/STATUS-CERRADO-success?style=for-the-badge)

> 🔒 **Versión académica cerrada**
>
> Esta versión corresponde a la entrega de la **Semana 2 / M6.1S2**.
> Se completa la evolución de Eventify desde almacenamiento en memoria
> hacia persistencia real mediante PostgreSQL.

---

# Descripción

**Eventify** es una API REST desarrollada con **Spring Boot** como base arquitectónica para una plataforma de gestión de eventos.

Durante la primera etapa del proyecto se construyó el cimiento arquitectónico utilizando almacenamiento temporal en memoria.

En esta segunda etapa, Eventify evoluciona hacia una arquitectura con **persistencia real**, utilizando:

* Spring Data JPA
* Hibernate
* PostgreSQL
* Docker
* CRUD completo
* Paginación
* Ordenamiento
* Validaciones
* Manejo de recursos inexistentes
* Pruebas de persistencia

El flujo principal de la aplicación es:

```text
HTTP Client
    ↓
Controller
    ↓
Service
    ↓
Repository
    ↓
Spring Data JPA
    ↓
Hibernate
    ↓
PostgreSQL
```

---

# Objetivo

El objetivo de M6.1S2 es transformar el almacenamiento temporal de Eventify en una solución persistente utilizando una base de datos relacional.

Se implementan las siguientes capacidades:

* Persistencia de eventos.
* Persistencia de venues.
* Operaciones CRUD.
* Consulta por identificador.
* Actualización de registros existentes.
* Eliminación física.
* Validación de datos.
* Manejo de errores HTTP.
* Paginación.
* Ordenamiento.
* Consultas derivadas mediante Spring Data JPA.
* Persistencia después de reiniciar la aplicación.
* Pruebas de repositorios mediante `@DataJpaTest`.

---

# Arquitectura

```text
┌──────────────────────────────────────────────┐
│                 HTTP Client                  │
│            Postman / Swagger / Browser       │
└──────────────────────┬───────────────────────┘
                       │
                       ▼
┌──────────────────────────────────────────────┐
│                 Controller                  │
│                REST Layer                   │
└──────────────────────┬───────────────────────┘
                       │
                       ▼
┌──────────────────────────────────────────────┐
│                   Service                   │
│                Business Logic               │
└──────────────────────┬───────────────────────┘
                       │
                       ▼
┌──────────────────────────────────────────────┐
│                 Repository                  │
│              Data Access Layer              │
└──────────────────────┬───────────────────────┘
                       │
                       ▼
┌──────────────────────────────────────────────┐
│               Spring Data JPA               │
│            JpaRepository / Queries          │
└──────────────────────┬───────────────────────┘
                       │
                       ▼
┌──────────────────────────────────────────────┐
│                  Hibernate                  │
│                    ORM                     │
└──────────────────────┬───────────────────────┘
                       │
                       ▼
┌──────────────────────────────────────────────┐
│                 PostgreSQL                  │
│                 Database                   │
└──────────────────────────────────────────────┘
```

## Flujo de una petición

Ejemplo:

```text
POST /api/events
       │
       ▼
EventController
       │
       ▼
EventService
       │
       ├── Validación
       │
       ▼
EventRepository
       │
       ▼
Spring Data JPA
       │
       ▼
Hibernate
       │
       ▼
PostgreSQL
```

Cada capa mantiene una responsabilidad específica.

---

# Estructura del proyecto

```text
eventify/
│
├── src/
│   │
│   ├── main/
│   │   ├── java/
│   │   │   └── com/eventify/
│   │   │       │
│   │   │       ├── EventifyApplication.java
│   │   │       │
│   │   │       ├── config/
│   │   │       │   └── DataSeeder.java
│   │   │       │
│   │   │       ├── controller/
│   │   │       │   ├── EventController.java
│   │   │       │   └── VenueController.java
│   │   │       │
│   │   │       ├── model/
│   │   │       │   ├── Event.java
│   │   │       │   └── Venue.java
│   │   │       │
│   │   │       ├── repository/
│   │   │       │   ├── EventRepository.java
│   │   │       │   └── VenueRepository.java
│   │   │       │
│   │   │       └── service/
│   │   │           ├── EventService.java
│   │   │           └── VenueService.java
│   │   │
│   │   └── resources/
│   │       ├── application.properties
│   │       └── application-seed.properties
│   │
│   └── test/
│       └── java/
│           └── com/eventify/
│               ├── repository/
│               │   ├── EventRepositoryTest.java
│               │   └── VenueRepositoryTest.java
│               │
│               └── service/
│                   ├── EventServiceTest.java
│                   └── VenueServiceTest.java
│
├── docker-compose.yml
├── pom.xml
├── .gitignore
└── README.md
```

---

# Modelos

## Event

Representa un evento dentro de la plataforma.

| Campo         | Tipo            | Configuración                                      | Descripción       |
| ------------- | --------------- | -------------------------------------------------- | ----------------- |
| `id`          | `Long`          | `@Id`, `@GeneratedValue`                           | Identificador     |
| `nombre`      | `String`        | `@NotBlank`, `@Column(nullable=false, length=100)` | Nombre del evento |
| `fecha`       | `LocalDateTime` | -                                                  | Fecha y hora      |
| `descripcion` | `String`        | -                                                  | Descripción       |

La entidad utiliza:

```java
@Entity
@Table(name = "events")
```

El identificador es generado automáticamente:

```java
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
```

---

## Venue

Representa el espacio físico donde puede realizarse un evento.

| Campo       | Tipo      | Configuración                                      | Descripción      |
| ----------- | --------- | -------------------------------------------------- | ---------------- |
| `id`        | `Long`    | `@Id`, `@GeneratedValue`                           | Identificador    |
| `nombre`    | `String`  | `@NotBlank`, `@Column(nullable=false, length=100)` | Nombre del venue |
| `direccion` | `String`  | -                                                  | Dirección física |
| `capacidad` | `Integer` | -                                                  | Capacidad máxima |

La entidad utiliza:

```java
@Entity
@Table(name = "venues")
```

---

# JPA y Hibernate

Eventify utiliza **Jakarta Persistence (JPA)** como especificación para mapear las clases Java hacia las tablas de PostgreSQL.

Hibernate actúa como implementación ORM.

La relación conceptual es:

```text
Clase Java
    ↓
@Entity
    ↓
Tabla SQL
```

Por ejemplo:

```text
Event
    ↓
events

Venue
    ↓
venues
```

## Principales anotaciones utilizadas

| Anotación         | Función                           |
| ----------------- | --------------------------------- |
| `@Entity`         | Define una clase como entidad JPA |
| `@Table`          | Define la tabla asociada          |
| `@Id`             | Define la clave primaria          |
| `@GeneratedValue` | Genera automáticamente el ID      |
| `@Column`         | Configura una columna             |
| `@Enumerated`     | Permite persistir enumeraciones   |
| `@NotBlank`       | Valida cadenas obligatorias       |

---

# Repositories

Los repositorios utilizan `JpaRepository`.

```java
public interface EventRepository
        extends JpaRepository<Event, Long> {
}
```

```java
public interface VenueRepository
        extends JpaRepository<Venue, Long> {
}
```

Al extender `JpaRepository`, Spring Data proporciona automáticamente operaciones como:

```text
save()
findAll()
findById()
deleteById()
existsById()
count()
```

También permite utilizar paginación:

```java
Page<Event> findAll(Pageable pageable);
```

---

# Consultas derivadas

Spring Data JPA permite crear consultas a partir del nombre del método.

Ejemplo:

```java
Page<Event> findByNombreContaining(
        String nombre,
        Pageable pageable
);
```

El método permite buscar eventos cuyo nombre contenga determinado texto.

Ejemplo conceptual:

```text
GET /api/events/search?nombre=tech
```

La consulta es generada automáticamente por Spring Data JPA.

---

# Services

La capa `Service` contiene la lógica de negocio y coordina las operaciones con el repositorio.

Ejemplo:

```java
@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }
}
```

La inyección se realiza mediante constructor.

---

# CRUD

Eventify implementa CRUD completo.

```text
C → Create
R → Read
U → Update
D → Delete
```

| Operación  | HTTP     | Endpoint           |
| ---------- | -------- | ------------------ |
| Crear      | `POST`   | `/api/events`      |
| Listar     | `GET`    | `/api/events`      |
| Buscar     | `GET`    | `/api/events/{id}` |
| Actualizar | `PUT`    | `/api/events/{id}` |
| Eliminar   | `DELETE` | `/api/events/{id}` |

Los mismos principios se aplican a:

```text
/api/venues
```

---

# Events API

## Crear evento

```http
POST /api/events
Content-Type: application/json
```

Body:

```json
{
    "nombre": "Tech Conference 2026",
    "fecha": "2026-10-15T09:00:00",
    "descripcion": "Conferencia sobre tecnología y desarrollo de software"
}
```

Respuesta:

```http
201 Created
```

> El `id` no necesita enviarse porque PostgreSQL lo genera automáticamente.

---

## Listar eventos

```http
GET /api/events
```

Respuesta:

```http
200 OK
```

---

## Obtener evento por ID

```http
GET /api/events/1
```

Si existe:

```http
200 OK
```

Si no existe:

```http
404 Not Found
```

---

## Actualizar evento

```http
PUT /api/events/1
Content-Type: application/json
```

Body:

```json
{
    "nombre": "Tech Conference 2026 Updated",
    "fecha": "2026-10-16T10:00:00",
    "descripcion": "Nueva descripción del evento"
}
```

Respuesta:

```http
200 OK
```

El servicio primero busca el registro existente y posteriormente actualiza sus atributos.

---

## Eliminar evento

```http
DELETE /api/events/1
```

Respuesta:

```http
204 No Content
```

Si el evento no existe:

```http
404 Not Found
```

---

# Venues API

## Crear venue

```http
POST /api/venues
Content-Type: application/json
```

Body:

```json
{
    "nombre": "Centro de Convenciones",
    "direccion": "Carrera 50 # 80-90",
    "capacidad": 500
}
```

Respuesta:

```http
201 Created
```

---

## Listar venues

```http
GET /api/venues
```

Respuesta:

```http
200 OK
```

---

## Obtener venue

```http
GET /api/venues/1
```

Respuesta:

```http
200 OK
```

---

## Actualizar venue

```http
PUT /api/venues/1
```

Body:

```json
{
    "nombre": "Centro de Convenciones Principal",
    "direccion": "Carrera 50 # 80-90",
    "capacidad": 600
}
```

Respuesta:

```http
200 OK
```

---

## Eliminar venue

```http
DELETE /api/venues/1
```

Respuesta:

```http
204 No Content
```

---

# Paginación

Eventify utiliza `Pageable` de Spring Data.

Ejemplo:

```http
GET /api/events?page=0&size=5
```

Significa:

```text
Página: 0
Cantidad de elementos: 5
```

La respuesta incluye información de paginación proporcionada por Spring Data.

Conceptualmente:

```json
{
    "content": [],
    "page": {
        "size": 5,
        "number": 0
    }
}
```

---

# Ordenamiento

También es posible ordenar los resultados.

Ejemplo:

```http
GET /api/events?sort=nombre,asc
```

Orden descendente:

```http
GET /api/events?sort=nombre,desc
```

Paginación + ordenamiento:

```http
GET /api/events?page=0&size=5&sort=nombre,asc
```

Otro ejemplo:

```http
GET /api/venues?page=0&size=10&sort=capacidad,desc
```

---

# Validaciones

Las entidades utilizan Bean Validation.

Por ejemplo:

```java
@NotBlank
@Column(nullable = false, length = 100)
private String nombre;
```

El controller activa estas validaciones mediante:

```java
@Valid
@RequestBody
```

Ejemplo:

```java
@PostMapping
@ResponseStatus(HttpStatus.CREATED)
public Event guardar(
        @Valid @RequestBody Event event) {

    return eventService.guardar(event);
}
```

Esto permite rechazar solicitudes con información inválida antes de realizar la persistencia.

---

# Manejo de recursos inexistentes

Las operaciones de consulta, actualización y eliminación verifican primero que el recurso exista.

Ejemplo:

```java
public Event buscarPorId(Long id) {
    return eventRepository.findById(id)
            .orElseThrow(() ->
                    new ResourceNotFoundException(
                            "Evento con ID " + id + " no encontrado"
                    ));
}
```

Esto permite diferenciar:

```text
Recurso encontrado
        ↓
200 OK
```

de:

```text
Recurso inexistente
        ↓
404 Not Found
```

La aplicación utiliza una excepción específica para recursos inexistentes y un manejador global mediante `@RestControllerAdvice`.

---

# Base de datos

La persistencia se realiza mediante PostgreSQL.

Configuración utilizada:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5433/eventify
spring.datasource.username=postgres
spring.datasource.password=postgres
```

Hibernate administra la comunicación ORM entre las entidades Java y PostgreSQL.

---

# Docker

PostgreSQL se ejecuta mediante Docker.

Configuración conceptual:

```text
Spring Boot
     │
     │ localhost:5433
     ▼
Docker
     │
     ▼
PostgreSQL 17
     │
     ▼
eventify
```

El puerto utilizado por el proyecto es:

```text
5433 → 5432
```

Donde:

```text
5433 = puerto del equipo local
5432 = puerto de PostgreSQL dentro del contenedor
```

---

# Configuración JPA

La aplicación utiliza Hibernate para administrar la persistencia.

Configuración principal:

```properties
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

### `ddl-auto=update`

Permite que Hibernate actualice el esquema de la base de datos de acuerdo con las entidades existentes sin eliminar automáticamente los datos existentes.

### `show-sql`

Permite visualizar las consultas SQL generadas por Hibernate.

### `format_sql`

Mejora el formato visual de las consultas SQL mostradas en consola.

---

# Persistencia real

A diferencia de la primera etapa, los datos ya no se almacenan en una colección temporal.

Anteriormente:

```text
Application
    ↓
List<Event>
```

Ahora:

```text
Application
    ↓
JpaRepository
    ↓
Hibernate
    ↓
PostgreSQL
```

Esto significa que los datos permanecen almacenados aunque la aplicación se detenga y vuelva a iniciarse.

---

# Data Seeder

Eventify mantiene un `DataSeeder` para cargar información inicial.

El Seeder utiliza un perfil específico:

```text
seed
```

La configuración puede activarse mediante:

```properties
spring.profiles.active=seed
```

El flujo es:

```text
DataSeeder
    │
    ├── VenueRepository
    │       ↓
    │   PostgreSQL
    │
    └── EventRepository
            ↓
        PostgreSQL
```

Datos iniciales utilizados durante el desarrollo:

```text
Venue:
Centro de Convenciones
Capacidad: 500

Event:
Tech Conference 2026
Fecha: 2026-10-15 09:00
```

---

# Inyección de dependencias

Eventify utiliza **constructor injection**.

Ejemplo:

```java
private final EventRepository eventRepository;

public EventService(EventRepository eventRepository) {
    this.eventRepository = eventRepository;
}
```

Spring administra las dependencias mediante su contenedor de Beans.

Esto evita crear manualmente los objetos:

```java
EventRepository repository = new EventRepository();
EventService service = new EventService(repository);
```

En su lugar:

```text
Spring Container
      │
      ├── EventRepository
      │
      └── EventService
```

---

# Estereotipos de Spring

| Anotación         | Responsabilidad            |
| ----------------- | -------------------------- |
| `@RestController` | Maneja peticiones HTTP     |
| `@Service`        | Contiene lógica de negocio |
| `@Repository`     | Representa acceso a datos  |
| `@Configuration`  | Define configuración       |
| `@Bean`           | Registra objetos en Spring |

---

# Testing

El proyecto utiliza:

* JUnit 5
* Mockito
* Spring Boot Test
* Spring Data JPA Test

## Pruebas unitarias

Las pruebas unitarias de servicios utilizan Mockito para aislar el repositorio.

Flujo:

```text
EventService
      │
      ▼
Mock EventRepository
      │
      ▼
    Mockito
```

Se comprueban casos como:

* Creación de eventos.
* Búsqueda por ID.
* Actualización.
* Eliminación.
* Validaciones.
* Manejo de eventos inexistentes.

---

# Pruebas de persistencia

Para comprobar el comportamiento de los repositorios se utiliza:

```java
@DataJpaTest
```

Estas pruebas permiten verificar la interacción entre:

```text
Entity
   ↓
JpaRepository
   ↓
JPA / Hibernate
   ↓
Base de datos de prueba
```

Se pueden comprobar operaciones como:

```text
save()
findById()
findAll()
deleteById()
findByNombreContaining()
```

---

# Swagger / OpenAPI

Eventify utiliza Swagger/OpenAPI para documentar y probar la API REST.

La interfaz está disponible en:

```text
http://localhost:8080/swagger-ui/index.html
```

También puede encontrarse en:

```text
http://localhost:8080/swagger-ui.html
```

Desde Swagger se pueden visualizar y ejecutar los endpoints disponibles.

---

# Códigos HTTP utilizados

| Código            | Significado                     | Uso                |
| ----------------- | ------------------------------- | ------------------ |
| `200 OK`          | Solicitud exitosa               | GET / PUT          |
| `201 Created`     | Recurso creado                  | POST               |
| `204 No Content`  | Operación exitosa sin contenido | DELETE             |
| `400 Bad Request` | Datos inválidos                 | Validaciones       |
| `404 Not Found`   | Recurso inexistente             | GET / PUT / DELETE |

---

# Ejemplos de paginación

Si existen 50 eventos:

```http
GET /api/events?page=0&size=5
```

La API devuelve:

```text
5 eventos
```

y proporciona metadatos sobre la paginación.

Segunda página:

```http
GET /api/events?page=1&size=5
```

Tercera página:

```http
GET /api/events?page=2&size=5
```

---

# Tecnologías

<p align="center">
    <img src="https://img.shields.io/badge/Java-26-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java 26">
    <img src="https://img.shields.io/badge/Spring%20Boot-4.1.1-6DB33F?style=for-the-badge&logo=springboot&logoColor=white" alt="Spring Boot 4.1.1">
    <img src="https://img.shields.io/badge/Spring%20MVC-REST-6DB33F?style=for-the-badge&logo=spring&logoColor=white" alt="Spring MVC">
    <img src="https://img.shields.io/badge/Spring%20Data-JPA-6DB33F?style=for-the-badge&logo=spring&logoColor=white" alt="Spring Data JPA">
    <img src="https://img.shields.io/badge/Hibernate-ORM-59666C?style=for-the-badge&logo=hibernate&logoColor=white" alt="Hibernate">
    <img src="https://img.shields.io/badge/PostgreSQL-17-4169E1?style=for-the-badge&logo=postgresql&logoColor=white" alt="PostgreSQL">
    <img src="https://img.shields.io/badge/Docker-Container-2496ED?style=for-the-badge&logo=docker&logoColor=white" alt="Docker">
    <img src="https://img.shields.io/badge/Lombok-1.18-BC4521?style=for-the-badge" alt="Lombok">
    <img src="https://img.shields.io/badge/Maven-Build-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white" alt="Maven">
    <img src="https://img.shields.io/badge/JUnit-5-25A162?style=for-the-badge&logo=junit5&logoColor=white" alt="JUnit 5">
    <img src="https://img.shields.io/badge/Mockito-Testing-78A641?style=for-the-badge" alt="Mockito">
    <img src="https://img.shields.io/badge/OpenAPI-Swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=black" alt="Swagger">
    <img src="https://img.shields.io/badge/ChatGPT-Generative%20AI-412991?style=for-the-badge&logo=openai&logoColor=white" alt="ChatGPT">
</p>

---

# Instalación

## Requisitos

Antes de ejecutar el proyecto se necesita:

* Java 26
* Maven
* Docker Desktop
* PostgreSQL 17
* IntelliJ IDEA u otro IDE compatible
* Git

Comprobar Java:

```bash
java -version
```

Comprobar Maven:

```bash
mvn -version
```

Comprobar Docker:

```bash
docker --version
```

---

# Base de datos

Crear la base de datos:

```sql
CREATE DATABASE eventify;
```

La conexión utilizada por Spring Boot es:

```text
Host: localhost
Port: 5433
Database: eventify
Username: postgres
Password: postgres
```

---

# Clonar el proyecto

```bash
git clone <URL_DEL_REPOSITORIO>
```

Entrar al proyecto:

```bash
cd eventify
```

---

# Ejecutar PostgreSQL

Levantar los servicios mediante Docker Compose:

```bash
docker compose up -d
```

Comprobar los contenedores:

```bash
docker ps
```

PostgreSQL debe encontrarse disponible mediante:

```text
localhost:5433
```

---

# Ejecutar Eventify

Desde IntelliJ IDEA:

```text
EventifyApplication.java
        ↓
Run
```

O mediante Maven:

```bash
mvn spring-boot:run
```

La aplicación estará disponible en:

```text
http://localhost:8080
```

---

# Endpoints

| Método   | Endpoint           | Descripción       | Respuesta     |
| -------- | ------------------ | ----------------- | ------------- |
| `POST`   | `/api/events`      | Crear evento      | `201 Created` |
| `GET`    | `/api/events`      | Listar eventos    | `200 OK`      |
| `GET`    | `/api/events/{id}` | Buscar evento     | `200 / 404`   |
| `PUT`    | `/api/events/{id}` | Actualizar evento | `200 / 404`   |
| `DELETE` | `/api/events/{id}` | Eliminar evento   | `204 / 404`   |
| `POST`   | `/api/venues`      | Crear venue       | `201 Created` |
| `GET`    | `/api/venues`      | Listar venues     | `200 OK`      |
| `GET`    | `/api/venues/{id}` | Buscar venue      | `200 / 404`   |
| `PUT`    | `/api/venues/{id}` | Actualizar venue  | `200 / 404`   |
| `DELETE` | `/api/venues/{id}` | Eliminar venue    | `204 / 404`   |

---

# Paginación y ordenamiento

Todos los endpoints de listado utilizan `Pageable`.

Ejemplos:

```http
GET /api/events?page=0&size=10
```

```http
GET /api/events?page=0&size=10&sort=nombre,asc
```

```http
GET /api/events?page=0&size=10&sort=fecha,desc
```

```http
GET /api/venues?page=0&size=5&sort=capacidad,desc
```

---

# Evolución del proyecto

## Semana 1 - M6.1S1

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
In-Memory Storage
```

Características:

* Arquitectura por capas.
* Spring MVC.
* Estereotipos de Spring.
* CRUD básico.
* Validaciones.
* Swagger/OpenAPI.
* JUnit.
* Mockito.
* Data Seeder.

## Semana 2 - M6.1S2

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Spring Data JPA
    ↓
Hibernate
    ↓
PostgreSQL
```

Se incorporan:

* Entidades JPA.
* `@Entity`.
* `@Table`.
* `@Id`.
* `@GeneratedValue`.
* `@Column`.
* Spring Data JPA.
* Hibernate.
* PostgreSQL.
* Docker.
* CRUD persistente.
* Paginación.
* Ordenamiento.
* Consultas derivadas.
* Validaciones.
* Manejo de `404 Not Found`.
* Pruebas `@DataJpaTest`.

---

# Principios arquitectónicos

Eventify mantiene una clara separación de responsabilidades.

```text
Controller  → HTTP y entrada/salida
Service     → Lógica de negocio
Repository  → Acceso a datos
Model       → Representación de entidades
Config      → Configuración de la aplicación
```

La comunicación principal es:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Database
```

Las capas superiores no acceden directamente a la base de datos.

---

# Estado del proyecto

| Característica                    | Estado |
| --------------------------------- | :----: |
| Spring Boot                       |    ✓   |
| Java 26                           |    ✓   |
| Spring MVC                        |    ✓   |
| Arquitectura por capas            |    ✓   |
| Event                             |    ✓   |
| Venue                             |    ✓   |
| JPA                               |    ✓   |
| Hibernate                         |    ✓   |
| PostgreSQL                        |    ✓   |
| Docker                            |    ✓   |
| Spring Data JPA                   |    ✓   |
| CRUD completo                     |    ✓   |
| Constructor Injection             |    ✓   |
| Validaciones                      |    ✓   |
| `404 Not Found`                   |    ✓   |
| Paginación                        |    ✓   |
| Ordenamiento                      |    ✓   |
| Consultas derivadas               |    ✓   |
| Data Seeder                       |    ✓   |
| Profile `seed`                    |    ✓   |
| Swagger / OpenAPI                 |    ✓   |
| JUnit 5                           |    ✓   |
| Mockito                           |    ✓   |
| `@DataJpaTest`                    |    ✓   |
| Persistencia después de reiniciar |    ✓   |

---

# Resultado de M6.1S2

Eventify evoluciona de una aplicación basada en almacenamiento temporal a una API REST con persistencia real.

```text
             M6.1S1
               │
               ▼
        Arquitectura base
               │
               ▼
          M6.1S2
               │
               ▼
     Persistencia estratégica
               │
               ▼
┌──────────────────────────────┐
│        Spring Boot           │
│          JPA                 │
│        Hibernate             │
│       PostgreSQL             │
│          Docker              │
│          CRUD                │
│    Pagination + Sorting      │
│       Testing                │
└──────────────────────────────┘
```

La segunda etapa establece una base persistente sobre la cual Eventify puede continuar incorporando nuevas funcionalidades y entidades.

---

# Autor

**Jesus David Lucena Quintero**

Proyecto académico desarrollado con:

**Java · Spring Boot · Spring Data JPA · Hibernate · PostgreSQL · Docker · REST API · JUnit · Mockito · OpenAPI**

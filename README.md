
## Estado del proyecto

![Status](https://img.shields.io/badge/STATUS-CERRADO-success?style=for-the-badge)

> 🔒 **Versión académica cerrada**
>
> Esta versión corresponde a la entrega final del proyecto.
> No se realizarán modificaciones sobre esta versión.

Eventify

### Cimiento arquitectónico para una plataforma de gestión de eventos

<p align="center">
    <img src="https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java 21">
    <img src="https://img.shields.io/badge/Spring%20Boot-4.1.1-6DB33F?style=for-the-badge&logo=springboot&logoColor=white" alt="Spring Boot">
    <img src="https://img.shields.io/badge/Maven-Build-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white" alt="Maven">
    <img src="https://img.shields.io/badge/JUnit-5-25A162?style=for-the-badge&logo=junit5&logoColor=white" alt="JUnit 5">
    <img src="https://img.shields.io/badge/Mockito-Testing-78A641?style=for-the-badge" alt="Mockito">
    <img src="https://img.shields.io/badge/ChatGPT-Generative%20AI-412991?style=for-the-badge&logo=openai&logoColor=white" alt="ChatGPT">
</p>

<p align="center">
  API REST desarrollada con Spring Boot siguiendo una arquitectura en capas,
  almacenamiento temporal en memoria, validaciones de negocio,
  documentación OpenAPI y pruebas unitarias.
</p>

---

## Descripción

**Eventify** es una API REST desarrollada con **Spring Boot** como base arquitectónica para una plataforma de gestión de eventos.

En esta primera etapa, el sistema permite registrar y consultar:

* Eventos
* Espacios o venues

La información se almacena temporalmente en memoria mediante colecciones de Java, permitiendo validar la arquitectura y la comunicación entre capas antes de incorporar una base de datos.

El proyecto implementa una separación clara de responsabilidades entre:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
In-memory Storage
```

---

## Objetivo

Construir el cimiento arquitectónico inicial de Eventify, validando:

* Arquitectura por capas.
* Inyección de dependencias mediante constructores.
* Uso de estereotipos de Spring.
* Reglas básicas de negocio.
* Endpoints REST.
* Códigos de respuesta HTTP.
* Documentación mediante Swagger/OpenAPI.
* Pruebas unitarias con JUnit 5 y Mockito.
* Carga inicial de información mediante un Data Seeder.

---

## Arquitectura

```text
┌──────────────────────────────────────┐
│              HTTP Client             │
│       Browser / Postman / Swagger    │
└──────────────────┬───────────────────┘
                   │
                   ▼
┌──────────────────────────────────────┐
│             Controller               │
│             REST Layer               │
└──────────────────┬───────────────────┘
                   │
                   ▼
┌──────────────────────────────────────┐
│               Service                │
│             Business Logic           │
└──────────────────┬───────────────────┘
                   │
                   ▼
┌──────────────────────────────────────┐
│             Repository               │
│           Data Access Layer          │
└──────────────────┬───────────────────┘
                   │
                   ▼
┌──────────────────────────────────────┐
│          In-Memory Storage            │
│              List<T>                 │
└──────────────────────────────────────┘
```

### Flujo de una petición

Por ejemplo:

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
List<Event>
```

Cada capa tiene una responsabilidad específica y no se salta las capas inferiores.

---

## Estructura del proyecto

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
│   │       └── application.properties
│   │
│   └── test/
│       └── java/
│           └── com/eventify/
│               └── service/
│                   ├── EventServiceTest.java
│                   └── VenueServiceTest.java
│
├── pom.xml
├── .gitignore
└── README.md
```

---

## Modelos

### Event

Representa un evento dentro de la plataforma.

| Campo         | Tipo            | Descripción              |
| ------------- | --------------- | ------------------------ |
| `id`          | `Long`          | Identificador del evento |
| `nombre`      | `String`        | Nombre del evento        |
| `fecha`       | `LocalDateTime` | Fecha y hora             |
| `descripcion` | `String`        | Descripción del evento   |

### Venue

Representa el espacio físico donde puede realizarse un evento.

| Campo       | Tipo      | Descripción             |
| ----------- | --------- | ----------------------- |
| `id`        | `Long`    | Identificador del venue |
| `nombre`    | `String`  | Nombre del espacio      |
| `direccion` | `String`  | Dirección física        |
| `capacidad` | `Integer` | Capacidad máxima        |

---

# API REST

## Events

### Crear evento

```http
POST /api/events
```

Ejemplo de solicitud:

```json
{
  "id": 1,
  "nombre": "Tech Conference 2026",
  "fecha": "2026-10-15T09:00:00",
  "descripcion": "Conferencia sobre tecnología y desarrollo de software"
}
```

Respuesta:

```http
201 Created
```

---

### Obtener eventos

```http
GET /api/events
```

Respuesta:

```http
200 OK
```

Ejemplo:

```json
[
  {
    "id": 1,
    "nombre": "Tech Conference 2026",
    "fecha": "2026-10-15T09:00:00",
    "descripcion": "Conferencia sobre tecnología y desarrollo de software"
  }
]
```

---

## Venues

### Crear venue

```http
POST /api/venues
```

Respuesta:

```http
201 Created
```

### Obtener venues

```http
GET /api/venues
```

Respuesta:

```http
200 OK
```

---

## Validaciones

Las reglas de negocio se encuentran en la capa `Service`.

Por ejemplo, un evento no puede crearse sin nombre:

```java
if (event.getNombre() == null || event.getNombre().isBlank()) {
    throw new IllegalArgumentException(
        "El nombre del evento es obligatorio"
    );
}
```

De esta manera:

```text
Controller
    │
    │ recibe la petición
    ▼
Service
    │
    │ valida
    ▼
Repository
    │
    │ almacena
    ▼
List
```

Si la información es inválida, el Repository no debe ser ejecutado.

---

# Data Seeder

Eventify utiliza un `DataSeeder` para cargar información inicial en la aplicación.

Se implementa mediante:

```text
@Configuration
@Bean
CommandLineRunner
@Profile("seed")
```

El flujo es:

```text
DataSeeder
     │
     ├── EventRepository
     │       └── Event inicial
     │
     └── VenueRepository
             └── Venue inicial
```

## Activar el Seeder

En:

```text
src/main/resources/application.properties
```

agregar:

```properties
spring.profiles.active=seed
```

Al iniciar la aplicación, Spring activará el perfil `seed` y ejecutará el `DataSeeder`.

---

# Inyección de dependencias

El proyecto utiliza **constructor injection**.

Ejemplo:

```java
private final EventRepository eventRepository;

public EventService(EventRepository eventRepository) {
    this.eventRepository = eventRepository;
}
```

Spring se encarga de crear y administrar las dependencias necesarias.

Esto permite evitar la creación manual de objetos:

```java
EventRepository repository = new EventRepository();
EventService service = new EventService(repository);
```

En su lugar, Spring administra estos objetos como Beans.

---

# Estereotipos de Spring

| Anotación         | Responsabilidad                            |
| ----------------- | ------------------------------------------ |
| `@RestController` | Maneja peticiones HTTP                     |
| `@Service`        | Contiene la lógica de negocio              |
| `@Repository`     | Gestiona el acceso a datos                 |
| `@Configuration`  | Define configuración de Spring             |
| `@Bean`           | Registra un objeto administrado por Spring |

---

# Testing

El proyecto utiliza **JUnit 5** y **Mockito** para realizar pruebas unitarias.

Las pruebas de servicio se realizan sin levantar el contexto completo de Spring.

```text
EventService
      │
      ▼
Mock EventRepository
      │
      ▼
    Mockito
```

### Caso exitoso

Se verifica que:

* El evento sea válido.
* El evento sea creado correctamente.
* `repository.save()` sea ejecutado.

### Caso inválido

Se verifica que:

* El nombre vacío sea rechazado.
* Se lance `IllegalArgumentException`.
* El Repository no sea llamado.

Ejemplo:

```java
Assertions.assertThrows(
    IllegalArgumentException.class,
    () -> eventService.create(event)
);

Mockito.verify(
    eventRepository,
    Mockito.never()
).save(Mockito.any());
```

---

# Swagger / OpenAPI

La API utiliza **Swagger/OpenAPI** para documentar los endpoints.

Una vez iniciada la aplicación, la interfaz puede consultarse en:

```text
http://localhost:8080/swagger-ui.html
```

También puede estar disponible en:

```text
http://localhost:8080/swagger-ui/index.html
```

Desde Swagger es posible visualizar y probar los endpoints de la API.

---

# Tecnologías

<p align="center">

<img src="https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java 21">

<img src="https://img.shields.io/badge/Spring%20Boot-4.1.1-6DB33F?style=for-the-badge&logo=springboot&logoColor=white" alt="Spring Boot 4.1.1">

<img src="https://img.shields.io/badge/Spring%20MVC-REST-6DB33F?style=for-the-badge&logo=spring&logoColor=white" alt="Spring MVC">

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

* Java 21
* Maven
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

---

## Clonar el proyecto

```bash
git clone <URL_DEL_REPOSITORIO>
```

Entrar al proyecto:

```bash
cd eventify
```

---

## Ejecutar

Desde IntelliJ IDEA:

```text
EventifyApplication.java
        ↓
Run
```

O desde Maven:

```bash
mvn spring-boot:run
```

La aplicación estará disponible en:

```text
http://localhost:8080
```

---

# Endpoints

| Método | Endpoint      | Descripción    | Estado        |
| ------ | ------------- | -------------- | ------------- |
| `POST` | `/api/events` | Crear evento   | `201 Created` |
| `GET`  | `/api/events` | Listar eventos | `200 OK`      |
| `POST` | `/api/venues` | Crear venue    | `201 Created` |
| `GET`  | `/api/venues` | Listar venues  | `200 OK`      |

---

# Persistencia

Actualmente Eventify utiliza almacenamiento temporal en memoria:

```java
private final List<Event> events = new ArrayList<>();
```

Esto significa que:

* Los datos existen mientras la aplicación está ejecutándose.
* Los datos se pierden al reiniciar.
* No existe todavía una base de datos.

Esta decisión permite concentrarse inicialmente en la arquitectura y en la comunicación entre las diferentes capas.

Posteriormente, el Repository puede reemplazar el almacenamiento en memoria por una base de datos sin modificar la responsabilidad de las demás capas.

---

# Principios arquitectónicos

El proyecto aplica los siguientes principios:

### Separación de responsabilidades

```text
Controller → HTTP
Service    → Business Logic
Repository → Data Access
Model      → Domain Data
Config     → Application Configuration
```

### Comunicación entre capas

```text
Controller
    ↓
Service
    ↓
Repository
```

Las capas superiores no acceden directamente a las capas inferiores saltándose responsabilidades.

---

# Estado del proyecto

| Característica         | Estado |
| ---------------------- | :----: |
| Spring Boot            |    ✓   |
| Java 21                |    ✓   |
| Arquitectura por capas |    ✓   |
| Event                  |    ✓   |
| Venue                  |    ✓   |
| Repository en memoria  |    ✓   |
| Service                |    ✓   |
| REST Controllers       |    ✓   |
| Constructor Injection  |    ✓   |
| Data Seeder            |    ✓   |
| Profile `seed`         |    ✓   |
| Swagger / OpenAPI      |    ✓   |
| JUnit 5                |    ✓   |
| Mockito                |    ✓   |
| HTTP 201 Created       |    ✓   |
| HTTP 200 OK            |    ✓   |

---

# Autor

**Jesus David Lucena Quintero**

Proyecto académico desarrollado con:

**Java · Spring Boot · REST API · JUnit · Mockito · OpenAPI**

---

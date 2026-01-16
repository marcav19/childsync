# ChildSync
Spring Boot REST API for logging daily occurences in a young child's life such as meals, naps, etc.

## Overview
This service provides CRUD operations for managing database entries regarding a child's meals, nap times, bathroom breaks, medications, appointments, baths, and events.

### Capabilities:
- Creation and deletion of entries
- Editing data within a record
- Retrieval of single or all entries within an occurence type

### Limitations:
- Sorting and filtering
- Authentication and authorization
- Error handling

## Tech Stack
- Java 21
- Spring Boot 3.5.6
- Spring Web
- Spring Data JPA
- MySQL 8.0
- Flyway (database migration)
- Swagger UI (OpenAPI)
- Docker

## Getting Started
### Requirements
- Java 21
- Docker Compose
- Git
### Run locally
#### Clone the repository:
```bash
git clone github.com/marcav19/childsync
```
#### Run the application:
```bash
docker compose up -d
./mvnw spring-boot:run
```

#### Access endpoints here:
**http://localhost:8080**

## Configuration
For database setup, the following environment variables are required:

| Variable | Description |
| -------- | ----------- |
| DB_URL | MySQL JDBC URL |
| DB_USER | Database username |
| DB_PASSWORD | Database password |

Variables can be accessed at /spring/src/main/resources/application-local.properties

## API Documentation
Swagger UI is utilized for API documentation. It can be accessed here:

**http://localhost:8080/swagger-ui.html**

### Example endpoint:
#### Create user:
```code
POST /api/user
```
Request body:
```json
{
    "user_name" : "John Smith"
    "user_email" : "john@aol.com"
}
```


## License
MIT
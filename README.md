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
- Spring Boot
- Spring Web
- Spring Data JPA
- Flyway (database migration)
- Swagger UI (OpenAPI)
- Docker
- Docker Compose

## Getting Started
### Requirements
- Java 21
- Docker Compose
- Git
### Run locally
#### Clone the repository:
```bash
git clone https://github.com/marcav19/childsync && cd childsync/
```
#### Run the application:
```bash
sudo docker compose up
```
#### Access endpoints here:
**http://localhost:8000**

## API Documentation
Swagger UI is utilized for API documentation. It can be accessed here:

**http://localhost:8000/swagger-ui.html**

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
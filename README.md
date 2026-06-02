# ChildSync
This application is designed to bridge the communication between parents, co-parents, grandparents, nannies, and babysitters and stay updated on the daily occurences of children.

## Overview
As a web app, it provides CRUD operations for managing entries regarding a child's meals, nap times, bathroom breaks, medications, appointments, baths, and activites.

### Capabilities:
- Creation, deletion, updating, and reading entries
- Validation of data entry
- Interactive dashboard

### Limitations:
- Sorting and filtering
- Authentication and authorization
- Custom error handling

## Tech Stack
- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- Flyway (database migration)
- Swagger UI (OpenAPI)
- Docker
- Docker Compose
- Angular 20

## Getting Started
### Requirements
- Java 21
- Docker Compose
- Git
- Angular 20
### Run locally
#### Clone the repository:
```bash
git clone https://github.com/marcav19/childsync
cd childsync/ && git checkout feature/add-ui
```
#### Run the application:
```bash
sudo docker compose up -d
```
#### Start Angular server:
```bash
cd ui/ && npm install
ng serve
```
#### Access application here:
**http://localhost:4200**

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
    "name" : "John Smith"
    "email" : "john@aol.com"
    "password" : "password"
}
```


## License
MIT
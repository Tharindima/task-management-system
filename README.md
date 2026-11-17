# Task Tracker

Beginner-friendly full-stack Task Management application.

## Tech Stack

- **Backend:** Spring Boot, Java 21, Spring Data JPA, Lombok
- **Frontend:** Angular 20
- **Database (local/dev):** H2 in-memory database

## What this project does

- Create tasks
- List all tasks
- Get a task by ID
- Update task description and status
- Delete tasks
- Validate request data and return clear errors

Task statuses:
- `TODO`
- `IN_PROGRESS`
- `DONE`

## Full Directory Overview

```text
task-tracker/
├── README.md
├── pom.xml
├── mvnw
├── mvnw.cmd
├── .gitignore
├── HELP.md
├── src/
│   ├── main/
│   │   ├── java/com/example/task_tracker/
│   │   │   ├── TaskTrackerApplication.java
│   │   │   ├── controller/
│   │   │   │   └── TaskController.java
│   │   │   ├── dto/
│   │   │   │   ├── TaskRequestDTO.java
│   │   │   │   └── TaskResponseDTO.java
│   │   │   ├── entity/
│   │   │   │   ├── Task.java
│   │   │   │   └── TaskStatus.java
│   │   │   ├── exception/
│   │   │   │   ├── GlobalExceptionHandler.java
│   │   │   │   └── ResourceNotFoundException.java
│   │   │   ├── mapper/
│   │   │   │   └── TaskMapper.java
│   │   │   ├── repository/
│   │   │   │   └── TaskRepository.java
│   │   │   └── service/
│   │   │       └── TaskService.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       ├── java/com/example/task_tracker/
│       │   ├── TaskTrackerApplicationTests.java
│       │   ├── TaskControllerTest.java
│       │   └── TaskServiceTest.java
│       └── resources/
│           └── application.properties
└── frontend/
    ├── package.json
    ├── angular.json
    ├── tsconfig.json
    ├── .gitignore
    ├── README.md
    └── src/
        ├── main.ts
        ├── styles.css
        └── app/
            ├── app.ts
            ├── app.html
            ├── app.css
            ├── app.config.ts
            ├── app.routes.ts
            ├── app.spec.ts
            ├── task.model.ts
            └── task.service.ts
```

## Backend API

Base URL: `http://localhost:8080`

| Method | Endpoint | Description |
|---|---|---|
| POST | `/tasks` | Create task |
| GET | `/tasks` | List all tasks |
| GET | `/tasks/{id}` | Get one task |
| PUT | `/tasks/{id}` | Update description + status |
| DELETE | `/tasks/{id}` | Delete task |

### Example request bodies

Create task:

```json
{
  "description": "Build backend API"
}
```

Update task:

```json
{
  "description": "Build frontend UI",
  "status": "IN_PROGRESS"
}
```

## Run Locally

### 1) Start backend

From project root:

```powershell
$env:JAVA_HOME="C:\Program Files\Java\jdk-21.0.12.1"
$env:Path="$env:JAVA_HOME\bin;$env:Path"
.\mvnw.cmd spring-boot:run
```

Backend URL:
- `http://localhost:8080`

### 2) Start frontend

In a second terminal:

```powershell
cd .\frontend
npm install
npm start
```

Frontend URL:
- `http://localhost:4200`

## Database configuration

This project is intentionally set to **H2** for easy local development (no PostgreSQL install required):

- URL: `jdbc:h2:mem:task_tracker;DB_CLOSE_DELAY=-1;MODE=PostgreSQL`
- User: `sa`
- Password: *(empty)*

Because this is an in-memory DB, data is cleared when the backend stops.

## Tests

From project root:

```powershell
.\mvnw.cmd test
```

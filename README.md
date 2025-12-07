# Employee Availability App

This project contains:

- **backend/** – Spring Boot application with:
  - Employee registration & login using JWT
  - Endpoints for updating and viewing your own availability
  - Monitoring endpoints: `/hello`, `/health`, `/actuator/health`, `/actuator/prometheus`
- **frontend/** – Angular application with:
  - Login page
  - Register page
  - Simple dashboard to view & edit your availability (only for the logged-in employee)
- **.github/workflows/** – GitHub Actions CI/CD that builds both backend and frontend on each push.

## Quick start

### Backend

Requirements: JDK 17+, Maven, MySQL running on `localhost:3306` with a database `employee_availability`
and user `appuser` / password `apppassword` (configurable in `backend/src/main/resources/application.properties`).

```bash
cd backend
mvn spring-boot:run
```

### Frontend

Requirements: Node 18+ and Angular CLI.

```bash
cd frontend
npm install
npm run build   # or: ng serve --open
```

The Angular app expects the backend at `http://localhost:8080`.

You can then:

- Register a new employee.
- Log in with that employee.
- Update your availability status.
- Check MySQL DB table `employees` to see the stored values.
- Access monitoring endpoints:
  - `GET http://localhost:8080/hello`
  - `GET http://localhost:8080/health`
  - `GET http://localhost:8080/actuator/health`
  - `GET http://localhost:8080/actuator/prometheus`

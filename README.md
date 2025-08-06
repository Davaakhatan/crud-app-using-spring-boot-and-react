# CRUD App Using Spring Boot & React

Full-stack CRUD application built with Java 21 Spring Boot backend and React frontend.

---

## 🚀 Technologies

- Java 21, Spring Boot (with Maven)
- React (Create React App), Axios, React Router, Bootstrap
- MySQL (local)
- Jakarta Bean Validation (Hibernate Validator)

---

## 🏗️ Project Structure

```
crud-app-using-spring-boot-and-react/
├── employee-service/       # Spring Boot backend project
├── react-frontend/         # React frontend project
└── README.md               # This documentation
```

---

## 🔧 Setup & Run Locally

### Backend (Spring Boot + MySQL)

1. Configure `application.properties` under `employee-service/src/main/resources/` with your MySQL credentials:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/ems
   spring.datasource.username=yourusername
   spring.datasource.password=yourpassword
   ```

2. Build and run:
   ```bash
   cd employee-service
   mvn clean package
   java -jar target/employee-service-0.0.1-SNAPSHOT.jar
   ```

Backend will start on **`http://localhost:8080`**.

### Frontend (Development Mode)

```bash
cd react-frontend
npm install
npm start
```

Frontend runs on **`http://localhost:3000`** and connects to the backend API.

### Frontend (Production Build into Backend)

```bash
cd react-frontend
npm run build
```

Copy build output into backend static folder:

```bash
cp -R build/* ../employee-service/src/main/resources/static/
```

Rebuild backend then visit **`http://localhost:8080/`** to serve the React UI.

---

## 📦 Build Automation (Optional)

Configure Maven to automate building React and copying to backend static folder using plugins like:
- [`frontend-maven-plugin`](https://github.com/eirslett/frontend-maven-plugin)
- [`maven-resources-plugin`](https://maven.apache.org/plugins/maven-resources-plugin/)

---

## 🔌 API Endpoints (Base: `/api/v1/employees`)

| HTTP Method | Endpoint             | Description      |
|-------------|----------------------|------------------|
| GET         | `/employees`         | List all employees |
| POST        | `/employees`         | Create a new employee |
| GET         | `/employees/{id}`    | Retrieve employee by ID |
| PUT         | `/employees/{id}`    | Update an existing employee |
| DELETE      | `/employees/{id}`    | Remove an employee |

### Validation Behaviors

- **Invalid input** returns `400 Bad Request` with structured JSON:
  ```json
  {
    "timestamp": "...",
    "status": 400,
    "errors": {
      "firstName": ["First name is required"],
      "email": ["Email should be valid"]
    },
    "path": "/api/v1/employees"
  }
  ```

- **Non-existent ID** returns `404 Not Found` with a structured error details object.

---

## 🧪 Testing with curl

```bash
curl -i -X POST http://localhost:8080/api/v1/employees \
  -H "Content-Type: application/json" \
  -d '{"firstName":"","lastName":"X","email":"invalid"}'
```

This should yield `400 Bad Request` with validation errors in the JSON payload.

---

## 👍 Usage Tips

- The React form integrates both front-end validation (before submission) and displays backend validation messages inline.
- Frontend routing for editing and creating employees is handled via `react-router-dom`.
- Both backend and frontend run concurrently during development; you only need the Spring Boot JAR to deploy the full stack in production.

---

## 📚 License & Contribution

This project is open source—feel free to fork, contribute, or customize as needed.  
Add a license file as appropriate for your reuse preferences.

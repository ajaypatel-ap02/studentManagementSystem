# 🧑‍🎓 Student Management System – Full Stack Project

A full-stack Student Management System built using:

- ⚙️ **Backend:** Java Servlets, JDBC, MySQL
- 🌐 **Frontend:** ReactJS
- 📦 **Build Tools:** Maven, Node.js
- 🚀 **Deployment:** Apache Tomcat 9

This project allows administrators to perform CRUD operations on student records.

---

## 📁 Project Structure

```

student-management-system/
├── student-management-backend/     # Java Servlet Backend (Tomcat 9)
│   ├── src/
│   └── pom.xml
│   └── README.md
├── STUDENT-MANAGEMENT-FRONTEND/    # React Frontend (Node.js)
│   ├── src/
│   │   ├── components/
│   │   ├── pages/
│   │   ├── services/
│   ├── package.json
│   └── README.md

````

---

## 🧩 Features

- Add, edit, delete, and view student records.
- Full integration between frontend and backend.
- Responsive user interface.
- RESTful API communication.
- JSON data exchange using Gson.
- CORS enabled for frontend-backend interaction.

---

## ⚙️ Technologies Used

### Backend

- Java 8+
- Java Servlets (javax.servlet-api-4.0.1)
- JDBC (MySQL)
- MySQL 8
- Gson 2.10
- Apache Tomcat 9
- Maven

### Frontend

- ReactJS (CRA)
- Axios
- React Router
- HTML5 + CSS3 + JSX

---

## 🏗️ Backend Setup

### 1. Database Configuration

Run the following SQL on MySQL:

```sql
CREATE DATABASE student_db;

USE student_db;

CREATE TABLE students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100),
    mobile VARCHAR(10),
    course VARCHAR(100)
);
````

### 2. Configure DB in `StudentDAO.java`

```java
String url = "jdbc:mysql://localhost:3306/student_db";
String user = "root";
String password = "your_password";
```

### 3. Build & Deploy

* Use `mvn clean package` to build a WAR.
* Deploy the WAR file to Tomcat 9 `webapps` folder.
* Start Tomcat and access:

  ```
  http://localhost:8081/studentManagementSystem
  ```

---

## 🌍 API Endpoints

| Method | Endpoint         | Description          |
| ------ | ---------------- | -------------------- |
| GET    | `/students`      | Get all students     |
| POST   | `/students`      | Add a new student    |
| PUT    | `/students/{id}` | Update student by ID |
| DELETE | `/students/{id}` | Delete student by ID |

---

## 🖥️ Frontend Setup

### 1. Navigate to frontend folder

```bash
cd STUDENT-MANAGEMENT-FRONTEND
```

### 2. Install Dependencies

```bash
npm install
```

### 3. Start React App

```bash
npm start
```

* The app runs on: `http://localhost:3000`
* Axios connects to the backend (`http://localhost:8080`)

> Make sure Tomcat is running and CORS is enabled in backend (`CORSFilter.java`).

---

## 🧠 Folder Highlights

### React Frontend

* `components/NavBar.jsx` – Navigation bar
* `pages/` – CRUD Pages: AddStudent, EditStudent, DeleteStudent, StudentList
* `services/studentService.js` – Axios service layer

### Java Backend

* `dao/StudentDAO.java` – Database operations
* `model/Student.java` – Data class
* `servlet/StudentServlet.java` – API controller
* `filter/CORSFilter.java` – CORS support

---

## 🧪 Testing the App

* Launch frontend: `http://localhost:3000`
* Make API requests from UI.
* Inspect requests via DevTools or Postman.

---

## 🛠️ Future Enhancements

* Form validation & alerts
* Authentication (JWT)
* Pagination and Search
* Export to Excel or PDF
* Spring Boot migration
* MongoDB Integration

---

## 👨‍💻 Developed By

**Ajay Patel**
🔗 GitHub: [ajaypatel-ap02](https://github.com/ajaypatel-ap02)

---

## 📜 License

This project is licensed under the MIT License.

```

---

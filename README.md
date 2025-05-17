# 🎓 Student Management System (Java + JDBC + MySQL)

A console-based Java application for managing student records using **JDBC** and **MySQL**. This project supports features like adding, viewing, updating, and deleting student information.

---

## 📁 Project Structure

```
studentManagementSystem/
├── database/
│   ├── mysql-connector-j-9.2.0.jar   # JDBC Driver
│   └── student_db.sql                # SQL script to create the database & table
├── src/
│   └── com.Ajay.studentManagementSystem/
│       ├── Main.java                 # Entry point of the application
│       ├── Student.java              # POJO class
│       ├── StudentDAO.java           # Handles DB operations
│       ├── StudentManagement.java    # Business logic / menu
│       └── Validator.java            # Input validation (email, mobile)
└── README.md                         # You're reading it 🙂
```

---

## 💡 Features

- ✅ Add a new student
- ✅ View all students
- 🔄 Update student info *(method present, needs UI integration)*
- ❌ Delete student *(method present, needs UI integration)*
- 📧 Email & mobile number validation using regex
- 🛠️ Modular code with clear separation of concerns

---

## 🛠 Technologies Used

- Java (JDK 8+)
- MySQL (via JDBC)
- IntelliJ IDEA (or any Java IDE)
- Regex (for validation)

---

## 🔧 Setup Instructions

### 1. Clone or Download the Repository
```bash
git clone https://github.com/your-username/studentManagementSystem.git
```

### 2. Database Setup

- Open **MySQL Workbench** or your terminal
- Run the SQL script:
```sql
-- student_db.sql
CREATE DATABASE IF NOT EXISTS student_db;

USE student_db;

CREATE TABLE IF NOT EXISTS students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100),
    mobile VARCHAR(10),
    course VARCHAR(100)
);
```

### 3. Update DB Credentials

In `StudentDAO.java`, update the following:
```java
String url = "jdbc:mysql://localhost:3306/student_db";
String user = "root";
String password = "your_password_here";
```

### 4. Add MySQL Connector JAR

- Download from [MySQL Connector/J](https://dev.mysql.com/downloads/connector/j/)
- Place the JAR in the `database/` or `lib/` folder
- In IntelliJ: File → Project Structure → Modules → Dependencies → Add JAR

---

## ▶️ Running the Project

Open your terminal or IntelliJ terminal and run:

```bash
javac src/com/Ajay/studentManagementSystem/Main.java
java com.Ajay.studentManagementSystem.Main
```

Choose from the available options:
```
Enter the operation number:
1 → Add Student
2 → View All Students
```

---

## 🚀 Future Improvements

- GUI Interface using JavaFX or Swing
- Search students by name or course
- Sort students by ID, name, or course
- Export student records to a CSV file
- Better exception handling & logging

---

## 📸 Sample Output

```bash
Enter the operation number for the operation
1
Enter student name
Ajay Patel
Enter student email
ajay@example.com
Enter student mobile number
9876543210
Enter student course
BCA
Student added successfully 1
```

---

## 📬 Contact

Made with ❤️ by [Ajay Patel](https://github.com/ajaypatel-ap02)

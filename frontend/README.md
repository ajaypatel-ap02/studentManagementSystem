# 🧑‍🎓 Student Management System – Frontend (ReactJS)

This is the **ReactJS frontend** for the Student Management System. It communicates with a backend Java Servlet API to perform CRUD operations on student data.

---

## 📁 Folder Structure

```
student-management-frontend/
├── public/
├── src/
│   ├── components/
│   │   └── NavBar.jsx
│   ├── pages/
│   │   ├── AddStudent.jsx
│   │   ├── EditStudent.jsx
│   │   └── StudentList.jsx
│   ├── services/
│   │   └── studentService.js
│   ├── App.css
│   ├── App.js
│   ├── App.test.js
│   ├── index.css
│   ├── index.js
│   ├── logo.svg
│   ├── reportWebVitals.js
│   └── setupTests.js
├── .gitignore
├── package.json
├── package-lock.json
└── README.md
```

---

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/ajaypatel-ap02/studentManagementSystem.git
cd studentManagementSystem/student-management-frontend
```

### 2. Install Dependencies

```bash
npm install
```

### 3. Configure API URL

In `src/services/studentService.js`, update the `BASE_URL` to match your backend deployment:

```js
const BASE_URL = 'http://localhost:8081/student-management-system-1.0/api/students';
```

### 4. Start the React Development Server

```bash
npm start
```

Frontend will run on:

```
http://localhost:3000
```

---

## 📦 Pages Overview

- **NavBar.jsx** – Navigation bar for easy routing
- **StudentList.jsx** – Displays all students
- **AddStudent.jsx** – Form to add a student
- **EditStudent.jsx** – Edit existing student info

---

## 📡 API Communication

All HTTP requests are handled through Axios in `services/studentService.js`.

| Method | Endpoint                        | Description                |
|--------|---------------------------------|----------------------------|
| GET    | /api/students                   | Fetch all students         |
| GET    | /api/students/{id}              | Fetch a student by ID      |
| POST   | /api/students                   | Add new student            |
| PUT    | /api/students/{id}              | Update existing student    |
| DELETE | /api/students/{id}              | Delete a student           |

---

## 💅 Styling

You can add your own styling in:

- `App.css`
- `index.css`

Or integrate with **Bootstrap** or **TailwindCSS** for a modern look.

---

## 🔒 Authentication & Panels

- **Login Page:** Planned for user/admin authentication.
- **Admin Panel:** Manage all students, view analytics.
- **User Panel:** View and edit own profile.

---

## 🔧 Testing

The project uses **Jest** (included with Create React App).

To run tests:

```bash
npm test
```

---

## ✅ To Do / Future Enhancements

- ✅ Form validation for student fields
- 🔒 Authentication (login, admin/user access)
- 🔍 Search & sort functionality
- 📊 Dashboard with charts
- 📦 Deploy to Vercel or Netlify

---

## 🤝 Contributing

Contributions are welcome! Please open an issue or submit a pull request.

---

## 👨‍💻 Developed By

[Ajay Patel](https://github.com/ajaypatel-ap02)

---

## 📝 License

This project is licensed under the MIT License.


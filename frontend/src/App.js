import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Navbar from "./components/NavBar";
import StudentList from "./pages/StudentList";
import AddStudent from "./pages/AddStudent";
import EditStudent from "./pages/EditStudent";
// import DeleteStudent from "./components/deleteStudent";

function App() {
  return (
    <div className="container mt-4">
      <Router>
        <Navbar />
        <h2 className="text-center mb-4">Student Management System</h2>
        <Routes>
          <Route path="/" element={<StudentList />} />
          <Route path="/add" element={<AddStudent />} />
          <Route path="/edit/:id" element={<EditStudent />} />
          {/* <Route path="/delete/:id" element={<DeleteStudent />} /> */}
        </Routes>
      </Router>
    </div>
  );
}

export default App;

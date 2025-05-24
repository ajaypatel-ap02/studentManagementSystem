import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { createStudent } from "../services/studentService";
  
function AddStudent() {
  const navigate = useNavigate();

  const [student, setStudent] = useState({
    name: "",
    mobile: "",
    email: "",
    course: "",

  });

  const handleChange = (e) => {
    setStudent({ ...student, [e.target.name]: e.target.value });
  };



// ...
const handleSubmit = async (e) => {
  e.preventDefault();
  try {
    await createStudent(student);
    navigate("/");
  } catch (error) {
    console.error("Error adding student:", error);
  }
};


  return (
    <div className="card p-4">
      <h3 className="mb-4">Add New Student</h3>
      <form onSubmit={handleSubmit}>
        <div className="mb-3">
          <label>Name</label>
          <input
            type="text"
            className="form-control"
            name="name"
            value={student.name}
            onChange={handleChange}
            required
          />
        </div>
        <div className="mb-3">
          <label>Mobile</label>
          <input
            type="number"
            className="form-control"
            name="mobile"
            value={student.mobile}
            onChange={handleChange}
            required
          />
        </div>
        <div className="mb-3">
          <label>Email</label>
          <input
            type="email"
            className="form-control"
            name="email"
            value={student.email}
            onChange={handleChange}
            required
          />
        </div>
        <div className="mb-3">
          <label>Course</label>
          <input
            type="text"
            className="form-control"
            name="course"
            value={student.course}
            onChange={handleChange}
            required
          />
        </div>
        <button type="submit" className="btn btn-success">
          Add Student
        </button>
      </form>
    </div>
  );
}

export default AddStudent;

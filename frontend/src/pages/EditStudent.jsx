import { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { getStudentById, updateStudent } from "../services/studentService";
function EditStudent() {
  const { id } = useParams();
  const navigate = useNavigate();

  const [student, setStudent] = useState({
    name: "",
    email: "",
    course: "",
  });

  const handleChange = (e) => {
    setStudent({ ...student, [e.target.name]: e.target.value });
  };

  

// ...

useEffect(() => {
  const fetchStudent = async () => {
    try {
      const res = await getStudentById(id);
      setStudent(res.data);
    } catch (error) {
      console.error("Error fetching student:", error);
    }
  };
  fetchStudent();
}, [id]);

const handleSubmit = async (e) => {
  e.preventDefault();
  try {
    await updateStudent(id, student);
    navigate("/");
  } catch (error) {
    console.error("Error updating student:", error);
  }
};


  return (
    <div className="card p-4">
      <h3 className="mb-4">Edit Student</h3>
      <form onSubmit={handleSubmit}>
        <div className="mb-3">
          <label>Name</label>
          <input
            type="text"
            className="form-control"
            name="name"
            value={student.name}
            onChange={handleChange}
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
          />
        </div>
        <button type="submit" className="btn btn-primary">
          Update Student
        </button>
      </form>
    </div>
  );
}

export default EditStudent;

import { useEffect, useState } from "react";
import { getAllStudents, deleteStudent } from "../services/studentService";
import { Link } from "react-router-dom";
// Import Font Awesome for icons (make sure to add the CDN in your index.html or install via npm)

function StudentList() {
  const [students, setStudents] = useState([]);
  const [loading, setLoading] = useState(true);

  const fetchStudents = async () => {
    try {
      const response = await getAllStudents();
      setStudents(response.data);
    } catch (error) {
      console.error("Error fetching students:", error);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchStudents();
  }, []);

  const handleDelete = async (id) => {
    if (window.confirm("Are you sure you want to delete this student?")) {
      try {
        await deleteStudent(id);
        fetchStudents();
      } catch (error) {
        console.error("Error deleting student:", error);
      }
    }
  };

  return (
    <div className="container mt-5 d-flex justify-content-center">
      <div className="card shadow w-100" style={{ maxWidth: 900 }}>
        <div className="card-body">
          <Link to="/add" className="btn btn-success mb-3">
            + Add Student
          </Link>
          {loading ? (
            <div className="text-center my-4">
              <div className="spinner-border" role="status"></div>
            </div>
          ) : students.length === 0 ? (
            <div className="alert alert-info text-center">
              No students added yet. Click + Add Student to begin.
            </div>
          ) : (
            <table className="table table-bordered table-striped table-hover">
              <thead className="table-light">
                <tr>
                  <th>Name</th>
                  <th>Email</th>
                  <th>Course</th>
                  <th>Actions</th>
                </tr>
              </thead>
              <tbody>
                {students.map((student) => (
                  <tr key={student.id}>
                    <td>{student.name}</td>
                    <td>{student.email}</td>
                    <td>{student.course}</td>
                    <td>
                      <Link
                        to={`/edit/${student.id}`}
                        className="btn btn-primary btn-sm me-2"
                        title="Edit"
                      >
                        <i className="fas fa-edit">Edit</i>
                      </Link>
                      <button
                        onClick={() => handleDelete(student.id)}
                        className="btn btn-danger btn-sm"
                        title="Delete"
                      >
                        <i className="fas fa-trash">Delete</i>
                      </button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          )}
        </div>
      </div>
    </div>
  );
}

export default StudentList;

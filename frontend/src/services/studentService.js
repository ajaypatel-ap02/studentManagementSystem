// src/services/studentService.js
import axios from "axios";

const BASE_URL = "http://localhost:8081/student-management-system-1.0/api/students";

export const getAllStudents = () => axios.get(BASE_URL, { withCredentials: true });
export const getStudentById = (id) => axios.get(`${BASE_URL}/${id}`);
export const createStudent = (student) => axios.post(BASE_URL, student);
export const updateStudent = (id, student) => axios.put(`${BASE_URL}/${id}`, student);
export const deleteStudent = (id) => axios.delete(`${BASE_URL}/${id}`);

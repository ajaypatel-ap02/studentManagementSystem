package com.Ajay.studentManagementSystem.dao;

import com.Ajay.studentManagementSystem.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Data Access Object (DAO) class for managing Student records in the database.
 * Handles all database operations including CRUD (Create, Read, Update, Delete).
 */
public class StudentDAO {
    // Logger instance for tracking operations and errors
    private static final Logger LOGGER = Logger.getLogger(StudentDAO.class.getName());

    // Static initialization block to load MySQL JDBC driver
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            LOGGER.severe("MySQL JDBC Driver not found. Add mysql-connector-java to your classpath!");
            throw new RuntimeException("MySQL JDBC Driver not found", e);
        }
    }

    // Prepared SQL statements for database operations
    private static final String INSERT_QUERY = "INSERT INTO students (name, email, mobile, course) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE students SET name=?, email=?, mobile=?, course=? WHERE id=?";
    private static final String DELETE_QUERY = "DELETE FROM students WHERE id=?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM students";
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM students WHERE id=?";

    // Database connection properties
    private final String url;      // Database URL
    private final String user;     // Database username
    private final String password; // Database password

    /**
     * Constructor initializes database connection parameters from environment variables.
     * Throws IllegalStateException if any required configuration is missing.
     */
    public StudentDAO() {
        this.url = "jdbc:mysql://localhost:3306/student_db";;
        this.user = "root";
        this.password = "Ajay@2710A";

    }

    /**
     * Creates and returns a new database connection.
     * @return Connection object to the database
     * @throws SQLException if connection fails
     */
    private Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Failed to establish database connection", e);
            throw e;
        }
    }

    /**
     * Adds a new student to the database.
     * @param student Student object to be added
     * @return true if successfully added, false if the operation fails
     */
    public boolean add(Student student) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {

            setStudentParameters(stmt, student);
            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating student failed, no rows affected.");
            }

            // Get and set the auto-generated ID
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    student.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating student failed, no ID obtained.");
                }
            }

            LOGGER.info("Successfully added student with ID: " + student.getId());
            return true;

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error adding student: " + student, e);
            return false;
        }
    }

    /**
     * Updates an existing student's information.
     * @param student Student object containing updated information
     * @return true if update successful, false otherwise
     */
    public boolean update(Student student) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE_QUERY)) {

            setStudentParameters(stmt, student);
            stmt.setInt(5, student.getId());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                LOGGER.info("Successfully updated student with ID: " + student.getId());
                return true;
            }
            return false;

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating student: " + student, e);
            return false;
        }
    }

    /**
     * Deletes a student from the database.
     * @param student Student object to be deleted
     * @return true if deletion successful, false otherwise
     */
    public boolean delete(Student student) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE_QUERY)) {

            stmt.setInt(1, student.getId());
            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                LOGGER.info("Successfully deleted student with ID: " + student.getId());
                return true;
            }
            return false;

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting student: " + student, e);
            return false;
        }
    }

    /**
     * Retrieves all students from the database.
     * @return List of all students, or null if operation fails
     */
    public List<Student> viewAll() {
        List<Student> students = new ArrayList<>();

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_ALL_QUERY)) {

            while (rs.next()) {
                students.add(mapResultSetToStudent(rs));
            }

            LOGGER.info("Successfully retrieved " + students.size() + " students");
            return students;

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving all students", e);
            return null;
        }
    }

    /**
     * Finds a student by their ID.
     * @param id The ID of the student to find
     * @return Student object if found, null otherwise
     */
    public Student find(int id) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID_QUERY)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Student student = mapResultSetToStudent(rs);
                    LOGGER.info("Successfully found student with ID: " + id);
                    return student;
                }
            }

            LOGGER.info("No student found with ID: " + id);
            return null;

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error finding student with ID: " + id, e);
            return null;
        }
    }

    /**
     * Helper method to set student parameters in a PreparedStatement.
     * @param stmt PreparedStatement to set parameters for
     * @param student Student object containing the data
     * @throws SQLException if parameter setting fails
     */
    private void setStudentParameters(PreparedStatement stmt, Student student) throws SQLException {
        stmt.setString(1, student.getName());
        stmt.setString(2, student.getEmail());
        stmt.setString(3, student.getMobile());
        stmt.setString(4, student.getCourse());
    }

    /**
     * Helper method to create a Student object from a ResultSet row.
     * @param rs ResultSet containing student data
     * @return Populated Student object
     * @throws SQLException if data retrieval fails
     */
    private Student mapResultSetToStudent(ResultSet rs) throws SQLException {
        Student student = new Student();
        student.setId(rs.getInt("id"));
        student.setName(rs.getString("name"));
        student.setEmail(rs.getString("email"));
        student.setMobile(rs.getString("mobile"));
        student.setCourse(rs.getString("course"));
        return student;
    }
}
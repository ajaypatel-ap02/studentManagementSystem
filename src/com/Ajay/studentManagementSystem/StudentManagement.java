package com.Ajay.studentManagementSystem;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Manages student-related operations in the Student Management System.
 * Provides functionality for adding, displaying, updating, and deleting student records.
 * Handles user interaction and data validation.
 */
public class StudentManagement {

    /**
     * Logger instance for tracking operations and errors
     */
    private static final Logger LOGGER = Logger.getLogger(StudentManagement.class.getName());

    /**
     * Constants for user interface messages to maintain consistency and ease maintenance
     */
    private static final String PROMPT_STUDENT_NAME = "Enter student name: ";
    private static final String PROMPT_STUDENT_COURSE = "Enter student course: ";
    private static final String PROMPT_STUDENT_ID = "Enter student ID: ";
    private static final String ERROR_STUDENT_NOT_FOUND = "Student not found with ID: %d";
    private static final String SUCCESS_STUDENT_ADDED = "Student added successfully!";
    private static final String SUCCESS_STUDENT_UPDATED = "Student updated successfully!";
    private static final String SUCCESS_STUDENT_DELETED = "Student deleted successfully!";
    private static final String ERROR_INVALID_INPUT = "Invalid input. Please try again.";

    /**
     * Core components for handling user input, validation, and data access
     */
    private final Scanner scanner;
    private final Validator validator;
    private final StudentDAO studentDAO;

    /**
     * Constructor initializes scanner, validator, and data access objects
     */
    public StudentManagement() {
        this.scanner = new Scanner(System.in);
        this.validator = new Validator();
        this.studentDAO = new StudentDAO();
    }

    /**
     * Adds a new student to the system.
     * Collects student information, validates it, and stores in database.
     * Handles and logs any errors during the process.
     */
    public void addStudent() {
        try {
            Student student = collectStudentInfo(new Student());
            if (studentDAO.add(student)) {
                LOGGER.info(SUCCESS_STUDENT_ADDED);
                System.out.println(SUCCESS_STUDENT_ADDED);
            }
        } catch (IllegalArgumentException e) {
            LOGGER.log(Level.WARNING, "Error adding student", e);
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Displays all students in a formatted table view.
     * Shows ID, Name, Email, Mobile, and Course information.
     * Handles empty database case and potential errors.
     */
    public void displayAllStudents() {
        try {
            List<Student> students = studentDAO.viewAll();
            if (students == null || students.isEmpty()) {
                System.out.println("No students found in the database.");
                return;
            }

            System.out.println("\n=== Student List ===");
            System.out.printf("%-5s %-20s %-25s %-15s %-20s%n",
                    "ID", "Name", "Email", "Mobile", "Course");
            System.out.println("=".repeat(85));

            students.forEach(this::displayStudentInFormat);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error retrieving students", e);
            System.out.println("Error retrieving student list. Please try again later.");
        }
    }

    /**
     * Updates existing student information.
     * Shows current information before updating.
     * Validates all new information before saving.
     * Handles cases where student is not found.
     */
    public void updateStudentInfo() {
        try {
            Optional<Student> studentOptional = findStudentById();
            if (studentOptional.isPresent()) {
                Student student = studentOptional.get();
                scanner.nextLine(); // Clear buffer

                System.out.println("\nCurrent student information:");
                displayStudentInFormat(student);
                System.out.println("\nEnter new information:");

                Student updatedStudent = collectStudentInfo(student);
                if (studentDAO.update(updatedStudent)) {
                    LOGGER.info(SUCCESS_STUDENT_UPDATED);
                    System.out.println(SUCCESS_STUDENT_UPDATED);
                }
            }
        } catch (IllegalArgumentException e) {
            LOGGER.log(Level.WARNING, "Error updating student", e);
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Deletes a student from the system.
     * Requires confirmation before deletion.
     * Shows student information before deletion.
     * Handles cases where student is not found.
     */
    public void deleteStudent() {
        try {
            Optional<Student> studentOptional = findStudentById();
            if (studentOptional.isPresent()) {
                Student student = studentOptional.get();
                System.out.println("\nStudent to delete:");
                displayStudentInFormat(student);

                if (confirmDeletion()) {
                    if (studentDAO.delete(student)) {
                        LOGGER.info(SUCCESS_STUDENT_DELETED);
                        System.out.println(SUCCESS_STUDENT_DELETED);
                    }
                } else {
                    System.out.println("Deletion cancelled.");
                }
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error deleting student", e);
            System.out.println("Error deleting student. Please try again later.");
        }
    }

    /**
     * Helper method to find a student by their ID.
     * Handles invalid input and non-existent students.
     * @return Optional<Student> containing the found student or empty if not found
     */
    private Optional<Student> findStudentById() {
        try {
            System.out.print(PROMPT_STUDENT_ID);
            int id = Integer.parseInt(scanner.nextLine().trim());
            Student student = studentDAO.find(id);

            if (student == null) {
                System.out.printf((ERROR_STUDENT_NOT_FOUND) + "%n", id);
                return Optional.empty();
            }
            return Optional.of(student);
        } catch (NumberFormatException e) {
            System.out.println(ERROR_INVALID_INPUT);
            return Optional.empty();
        }
    }

    /**
     * Collects and validates student information from user input.
     * Handles validation for name, email, mobile, and course.
     * @param student Existing or new Student object to populate
     * @return Student object with collected information
     * @throws IllegalArgumentException if required fields are empty
     */
    private Student collectStudentInfo(Student student) {
        System.out.print(PROMPT_STUDENT_NAME);
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        student.setName(name);

        // Email validation with retry
        String email = validator.validateEmail();
        student.setEmail(email);

        // Mobile validation with retry
        String mobile = validator.validateMobile();
        student.setMobile(mobile);

        System.out.print(PROMPT_STUDENT_COURSE);
        String course = scanner.nextLine().trim();
        if (course.isEmpty()) {
            throw new IllegalArgumentException("Course cannot be empty");
        }
        student.setCourse(course);

        return student;
    }

    /**
     * Displays a single student's information in formatted table row.
     * @param student Student object to display
     */
    private void displayStudentInFormat(Student student) {
        System.out.printf("%-5d %-20s %-25s %-15s %-20s%n",
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getMobile(),
                student.getCourse()
        );
    }

    /**
     * Prompts for and handles deletion confirmation.
     * @return true if user confirms deletion, false otherwise
     */
    private boolean confirmDeletion() {
        System.out.print("Are you sure you want to delete this student? (y/N): ");
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("y");
    }
}
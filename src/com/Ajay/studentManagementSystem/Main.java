package com.Ajay.studentManagementSystem;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Main class for the Student Management System application.
 * Handles the user interface and program flow control.
 */
public class Main {
    // Logger instance for recording application events and errors
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    
    // Multi-line string constant defining the main menu interface
    private static final String MENU = """
            \n=== Student Management System ===
            1. Add New Student
            2. View All Students
            3. Update Student
            4. Delete Student
            5. Exit
            
            Enter your choice (1-5): """;

    // Core components for managing students and handling user input
    private final StudentManagement studentManagement;
    private final Scanner scanner;

    /**
     * Constructor initializes the student management system and input scanner.
     */
    public Main() {
        this.studentManagement = new StudentManagement();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Main program loop that displays menu and processes user input.
     * Continues running until user selects exit option or an unrecoverable error occurs.
     */
    public void start() {
        while (true) {
            try {
                // Display menu and get user choice
                System.out.print(MENU);
                String choice = scanner.nextLine().trim();

                // Process user choice using switch expression
                switch (choice) {
                    case "1" -> studentManagement.addStudent();         // Add new student
                    case "2" -> studentManagement.displayAllStudents(); // Display all students
                    case "3" -> studentManagement.updateStudentInfo();  // Update existing student
                    case "4" -> studentManagement.deleteStudent();      // Delete student
                    case "5" -> {
                        System.out.println("Thank you for using Student Management System!");
                        return;  // Exit the program
                    }
                    default -> System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                }

            } catch (Exception e) {
                // Log any unexpected errors and show user-friendly message
                LOGGER.log(Level.SEVERE, "Unexpected error in main menu", e);
                System.out.println("An unexpected error occurred. Please try again.");
            }
        }
    }

    /**
     * Application entry point. Sets up logging and starts the application.
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        // Initialize logging configuration
        configureLogging();

        // Log application startup
        LOGGER.info("Starting Student Management System");
        try {
            // Create and start the main application
            new Main().start();
        } catch (Exception e) {
            // Log and display any critical startup errors
            LOGGER.log(Level.SEVERE, "Application failed to start", e);
            System.out.println("Application failed to start. Please check the logs.");
        }
    }

    /**
     * Configures the logging system for the application.
     * TODO: Implement specific logging configuration (file handlers, formatting, etc.)
     */
    private static void configureLogging() {
        // Add your logging configuration here
        // For example, setting up file handlers, formatting, etc.
    }
}
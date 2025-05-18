package com.Ajay.studentManagementSystem;

import java.util.Objects;

/**
 * Represents a student entity in the Student Management System.
 * Contains student's personal and academic information with validation.
 */

public class Student {

    /**
     * Student attributes
     */
    private int id;        // Unique identifier for the student
    private String name;   // Student's full name
    private String email;  // Student's email address
    private String mobile; // Student's mobile number
    private String course; // Course enrolled by the student

    /**
     * Default constructor creates an empty student object
     */

    public Student() {}

    /**
     * Full constructor to create a student with all attributes
     * @param id Unique identifier for the student
     * @param name Student's full name
     * @param email Student's email address
     * @param mobile Student's mobile number
     * @param course Course enrolled by student
     * @throws IllegalArgumentException if any parameter fails validation
     */
    public Student(int id, String name, String email, String mobile, String course) {
        this.id = id;
        this.setName(name);
        this.setEmail(email);
        this.setMobile(mobile);
        this.setCourse(course);
    }

    /**
     * Retrieves the student's ID
     * @return the student's unique identifier
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the student's ID
     * @param id must be a non-negative integer
     * @throws IllegalArgumentException if ID is negative
     */
    public void setId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("ID cannot be negative");
        }
        this.id = id;
    }

    /**
     * Retrieves the student's name
     * @return the student's full name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the student's name with validation
     * @param name Name to set
     * @throws IllegalArgumentException if name is null or empty
     */
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name.trim();
    }

    /**
     * Retrieves the student's email address
     * @return the student's email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the student's email with validation
     * @param email Email address to set
     * @throws IllegalArgumentException if email is null or empty
     */
    public void setEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        this.email = email.trim();
    }

    /**
     * Gets the student's mobile number
     * @return student mobile number
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * Sets the student's mobile number with validation
     * @param mobile Mobile number to set
     * @throws IllegalArgumentException if mobile number is null or empty
     */

    public void setMobile(String mobile) {
        if (mobile == null || mobile.trim().isEmpty()) {
            throw new IllegalArgumentException("Mobile number cannot be null or empty");
        }
        this.mobile = mobile.trim();
    }

    /**
     * Gets the student's enrolled course
     * @return course name
     */
    public String getCourse() {
        return course;
    }

    /**
     * Sets the student's course with validation
     * @param course Course name to set
     * @throws IllegalArgumentException if course is null or empty
     */
    public void setCourse(String course) {
        if (course == null || course.trim().isEmpty()) {
            throw new IllegalArgumentException("Course cannot be null or empty");
        }
        this.course = course.trim();
    }

    /**
     * Compares this student with another object for equality
     * Two students are considered equal if all their attributes match
     * @param o Object to compare with
     * @return true if objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student student)) return false;
        return id == student.id &&
                Objects.equals(name, student.name) &&
                Objects.equals(email, student.email) &&
                Objects.equals(mobile, student.mobile) &&
                Objects.equals(course, student.course);
    }

    /**
     * Generates a hash code for the student based on all attributes
     * @return hash code value
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, mobile, course);
    }

    /**
     * Returns a string representation of the student
     * Includes all student attributes in a formatted string
     * @return formatted string with student information
     */
    @Override
    public String toString() {
        return String.format("Student{id=%d, name='%s', email='%s', mobile='%s', course='%s'}",
                id, name, email, mobile, course);
    }
}

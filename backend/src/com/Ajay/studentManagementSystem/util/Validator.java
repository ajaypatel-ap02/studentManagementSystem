package com.Ajay.studentManagementSystem.util;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Validator class for handling input validation in the Student Management System.
 * Provides methods to validate mobile numbers and email addresses using regex patterns.
 */
public class Validator {
    // Scanner object for reading user input
    Scanner sc = new Scanner(System.in);

    /**
     * Validates a mobile number input from the user.
     * Valid mobile number must:
     * - Start with a digit between 6-9
     * - Contain exactly 10 digits
     * 
     * @return validated mobile number as String
     */
    public String validateMobile(){
        String mobile;
        // Regex pattern for Indian mobile numbers
        // ^     - start of string
        // [6-9] - first digit must be 6,7,8 or 9
        // [0-9] - matches any digit (0-9)
        // {9}   - exactly 9 occurrences of previous pattern
        // $     - end of string
        String mobileRegex= "^[6-9][0-9]{9}$";
        
        do{
            System.out.println("Enter student mobile number");
            mobile = sc.nextLine();

            if (Pattern.matches(mobileRegex,mobile)) {
                break;  // Exit loop if mobile number is valid
            }
            System.out.println("Invalid Mobile number!!!");
        }while (true);
        
        return mobile;
    }

    /**
     * Validates an email address input from the user.
     * Email must follow standard format: username@domain.extension
     * 
     * @return validated email address as String
     */
    public String validateEmail(){
        String email;
        // Regex pattern for email validation
        // ^[a-zA-Z0-9._%+-]+ - Username: letters, numbers, and certain special characters
        // @                   - Mandatory @ symbol
        // [a-zA-Z.-]+        - Domain name: letters, dots, and hyphens
        // \.                 - Mandatory dot
        // [a-zA-Z]{2,}       - Top-level domain: 2 or more letters
        // $                  - End of string
        String emailRegex= "^[a-zA-Z0-9._%+-]+@[a-zA-Z.-]+\\.[a-zA-Z]{2,}$";
        
        do{
            System.out.println("Enter student email");
            email = sc.nextLine();

            if(Pattern.matches(emailRegex,email)) {
                break;  // Exit loop if email is valid
            }

            System.out.println("Invalid Input");
        }while (true);
        
        return email;
    }
}
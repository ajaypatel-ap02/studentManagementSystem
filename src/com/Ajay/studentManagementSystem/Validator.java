package com.Ajay.studentManagementSystem;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Validator {
    Scanner sc = new Scanner(System.in);

    public String validateMobile(){
        String mobile;
        String mobileRegex= "^[6-9][0-9]{9}$";
        do{
            System.out.println("Enter student mobile number");
            mobile = sc.nextLine();

            if (Pattern.matches(mobileRegex,mobile)) {
                break;
            }
            System.out.println("Invalid Mobile number!!!");
        }while (true);
        return mobile;
    }

    public String validateEmail(){
        String email;
        String emailRegex= "^[a-zA-Z0-9._%+-]+@[a-zA-Z.-]+\\.[a-zA-Z]{2,}$";
        do{
            System.out.println("Enter student email");
            email = sc.nextLine();

            if(Pattern.matches(emailRegex,email)) {
                break;
            }

            System.out.println("Invalid Input");
        }while (true);
        return email;
    }

}

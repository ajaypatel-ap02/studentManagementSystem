package com.Ajay.studentManagementSystem;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentManagement studentManagement = new StudentManagement();



    while(true){
        System.out.println("Enter the operation number for the operation ");
        System.out.println("1. Add Student \n2. View All student \n3. Update student data \n4. Find student \n5. Delete student\n6. Exit");
        int operation = sc.nextInt();
        switch (operation){
            case 1:
                studentManagement.addStudent();
                break;
            case 2:
                studentManagement.viewAllStudents();
                break;
            case 3:
                studentManagement.updateStudentInfo();
             break;
            case 4:
                studentManagement.findStudent();
             break;
            case 5:
                studentManagement.deleteStudent();
             break;
             case 6:
                 System.out.println("Exiting the system");
                return;
            default:
                System.out.println("Invalid input");
        }

    }

    }
}

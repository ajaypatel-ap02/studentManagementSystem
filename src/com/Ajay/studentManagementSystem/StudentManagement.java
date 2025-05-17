package com.Ajay.studentManagementSystem;

import java.util.List;
import java.util.Scanner;

public class StudentManagement {
    static int i =0;
    Scanner sc = new Scanner(System.in);
    Validator validator = new Validator();
    StudentDAO studentDAO = new StudentDAO();
    public void addStudent(){
       Student student = new Student();


        System.out.println("Enter student name");
        student.setName(sc.nextLine());

        student.setEmail(validator.validateEmail());
        student.setMobile(validator.validateMobile());

        System.out.println("Enter student course");
        student.setCourse(sc.nextLine());


        studentDAO.add(student);
    }

    public void viewAllStudents(){

        System.out.println("All Students:- \n");
        List<Student> studentList = studentDAO.viewAll();
        for (Student student:studentList){
            System.out.println(student.toString());
        }

    }
    public Student findStudent(){
        System.out.println("Enter student id no. ");
        int find = sc.nextInt();
        Student student = studentDAO.find(find);
        if (student!=null)
            return student;

        System.out.println("no student found with this id ");
        return null;
    }
    public void updateStudentInfo(){
        Student student = findStudent();
        sc.nextLine();
        if(student != null){
            System.out.println("Enter student name");
            student.setName(sc.nextLine());

            student.setEmail(validator.validateEmail());
            student.setMobile(validator.validateMobile());

            System.out.println("Enter student course");
            student.setCourse(sc.nextLine());

            studentDAO.update(student);
            System.out.println("Student Updated Successfully.!!");
        }
        else
            return;
    }
    public void deleteStudent(){
        Student student = findStudent();
        if(student != null){
            studentDAO.delete(student);
            System.out.println("Student deleted successfully..!!");
            return;
        }
        System.out.println("no student found with this id ");
        return;
    }
}

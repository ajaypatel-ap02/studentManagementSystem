package com.Ajay.studentManagementSystem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

   String url = System.getenv("DB_URL");
   String user = System.getenv("DB_USER");
   String password = System.getenv("DB_PASSWORD");
   Connection getConnect(){
      try{
         return DriverManager.getConnection(url, user, password);
      }catch (SQLException e){
         System.out.println("Database Connection failed..!!");
      }
      return null;
   }
   void add(Student student){
      try{

         Connection connection = getConnect();

         if (connection!=null){

            Statement statement = connection.createStatement();

//         statement.executeQuery("use database student_db");
//         statement.executeQuery("CREATE TABLE stud(id int primary key AUTO_INCREMENT, name VARCHAR(100), email varchar(100), mobile varchar(10)");

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO students (name, email, mobile, course)values(?,?,?,?)");

            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getEmail());
            preparedStatement.setString(3, student.getMobile());
            preparedStatement.setString(4, student.getCourse());

            int insert = preparedStatement.executeUpdate();
            if(insert>0)
               System.out.println("Student added successfully "+insert);

            preparedStatement.close();
            statement.close();
            connection.close();

         }

      }catch(SQLException e){
         System.out.println(e.getMessage());
      }

   }
   void update(Student student){
      try{
         Connection connection = getConnect();
         PreparedStatement preparedStatement = connection.prepareStatement("UPDATE students SET name=?, email=?, mobile=?, course=? WHERE id=?");
         preparedStatement.setInt(5,student.getId());
         preparedStatement.setString(1, student.getName());
         preparedStatement.setString(2, student.getEmail());
         preparedStatement.setString(3, student.getMobile());
         preparedStatement.setString(4, student.getCourse());
         preparedStatement.executeUpdate();
         preparedStatement.close();
         connection.close();
      }catch(SQLException e){
         System.out.println(e.getMessage());
      }

   }
   List<Student> viewAll(){
//      Student student = new Student();
      List<Student> students = new ArrayList<Student>();
      try {
         Connection connection = getConnect();

         if (connection!=null) {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM students");
            while (resultSet.next()){
               students.add(mapRowToUser(resultSet));
            }
            statement.close();
            connection.close();
            return students;
         }
      }catch (SQLException e){
         System.out.println(e.getMessage());
      }
         return null;

   }
   public static Student mapRowToUser(ResultSet resultSet) throws SQLException {
      return new Student(
         resultSet.getInt("id"),
         resultSet.getString("name"),
         resultSet.getString("email"),
         resultSet.getString("mobile"),
         resultSet.getString("course")
      );
   }

   Student find(int id){
      try {
         Connection connection = getConnect();

         if (connection != null) {
            String query = "SELECT * FROM students WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            Student student = new Student();

            if (rs.next()) {
               student.setId(rs.getInt("id"));
               student.setName(rs.getString("name"));
               student.setEmail(rs.getString("email"));
               student.setMobile(rs.getString("mobile"));
               student.setCourse(rs.getString("course"));

               rs.close();
               preparedStatement.close();
               connection.close();
               return student;
            }

            rs.close();
            preparedStatement.close();
            connection.close();
         }
      } catch (SQLException e) {
         System.out.println(e.getMessage());
      }
      return null; // If student not found or an error occurred
   }
   void delete(Student student){
      try{
         Connection connection = getConnect();
         PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM students WHERE id=?");
         preparedStatement.setInt(1,student.getId());
         preparedStatement.executeUpdate();
         preparedStatement.close();
         connection.close();



      }catch (SQLException e){
         System.out.println(e.getMessage());
      }

   }
}

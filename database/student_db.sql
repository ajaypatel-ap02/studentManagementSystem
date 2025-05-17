CREATE DATABASE IF NOT EXISTS student_db;

USE student_db;

CREATE TABLE IF NOT EXISTS students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100),
    mobile VARCHAR(10),
    course VARCHAR(100)
);

INSERT INTO students(name, email, mobile, course)VALUES
('Ajay Patel', 'ajaypatel@gmail.com', '9099989796', 'java'),
('Nayan Patil', 'nayan@gmail.com', '8988878685' ,'Web Development');

SELECT * FROM students;

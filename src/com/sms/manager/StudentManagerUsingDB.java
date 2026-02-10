package com.sms.manager;
import java.sql.*;

// running commands
// PS C:\Users\kamer\JProjects\StudentManagementSystem\src> javac -cp ".;lib/mysql-connector-j-9.5.0.jar" -d bin com/sms/tester/StudentManagementTester.java
// PS C:\Users\kamer\JProjects\StudentManagementSystem\src> java -cp "bin;../lib/mysql-connector-j-9.5.0.jar" com.sms.tester.StudentManagementTester

import com.sms.model.StudentDb;
// import com.sms.model.*;
import com.sms.utils.*;

public class StudentManagerUsingDB {
  private final String url = "jdbc:mysql://localhost:3306/stdb?useSSL=false&serverTimezone=UTC";
  private final String user = "root";
  private final String pass = System.getenv("SQL_pass");
  private ValidateEmailAndPhoneNumber v;
  private Connection con;
  public StudentManagerUsingDB(){
    this.v = new ValidateEmailAndPhoneNumber();
    try{
      // Class.forName("com.mysql.cj.jdbc.Driver");
      // this.con = DriverManager.getConnection(url,user,pass);
      this.con = new StudentDb().getConnectionImp(url, user, pass);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  public void addNewStudent(String stId, String stName, String phn, String email, String city, String country, int pincode ) throws InvalidStudentDetailsException{
    try {
      // System.out.println("Connection Established Successfully");
      // PreparedStatement pstm = con.prepareStatement("select studentId from Student where studentId = ?");
      // pstm.setString(1, stId);
      // ResultSet rs = pstm.executeQuery();
      // if(rs.next()){
        //   System.out.println("Student with studentId "+stId+" already exists, enter correct studentId");
        //   return;
        // }
        
      if(isStudentExists(stId)){
        System.out.println("Student with studentId "+stId+" already exists");
        return;
      }

      v.ValidatePhoneNumber(phn);
      v.ValidateEmail(email);
      // System.out.println(con);
      PreparedStatement pstmForSt = con.prepareStatement("Insert into Student (studentId, studentName) Values (?,?)");

      pstmForSt.setString(1, stId);
      pstmForSt.setString(2, stName);
      pstmForSt.executeUpdate();

      PreparedStatement pstmForCont = con.prepareStatement("Insert into Contact (studentId, phoneNum, emailAddress) Values (?,?,?)");

      pstmForCont.setString(1, stId);
      pstmForCont.setString(2, phn);
      pstmForCont.setString(3, email);

      pstmForCont.executeUpdate();

      
      PreparedStatement pstmForAdd = con.prepareStatement("Insert into Address (studentId, city, country, pincode) Values (?,?,?,?)");
      
      pstmForAdd.setString(1, stId);
      pstmForAdd.setString(2, city);
      pstmForAdd.setString(3, country);
      pstmForAdd.setLong(4, pincode);

      pstmForAdd.executeUpdate();

      pstmForCont.close();
      pstmForAdd.close();
      pstmForSt.close();
      // con.close();
      System.out.println("=".repeat(150));
      System.out.println("Student with new studentId "+stId+" added successfully");
      System.out.println("=".repeat(150));
      
    } catch (Exception e) {
      // System.out.println(e.getMessage());
      e.printStackTrace();
    }
    // Student st = new StudentImplementor();
    // st.setStudentId(stId);
    // st.setStudentName(stName);
    // st.setContactInfo(phn, email);
    // st.setAddressInfo(city, country, pincode);
    // stSet.add(st);
  }

  public void removeStudent(String stId){
      try {
        PreparedStatement pstm = con.prepareStatement("delete from Student where studentId = ? ");
        pstm.setString(1, stId);
        int rows = pstm.executeUpdate();
        if (rows != 0)
          System.out.println("Student with studentId "+stId+" removed successfully");
        else
          System.out.println("No student record found with studentId "+stId);
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }    
    
    // Student st = findStudent(stId);
    // stSet.remove(st);
    // System.out.println("=".repeat(150));
    // System.out.println("Student with studentId "+stId+" removed successfully");
    // System.out.println("=".repeat(150));
  }
  
  public void viewStudent(String stId){
    // Student st = findStudent(stId);
    // if(!isStudentExists(stId) || st.equals(null)){
    //   System.out.println("=".repeat(150));
    //   System.out.println("Student with studentId "+stId+" does not exist");
    //   System.out.println("=".repeat(150));
    //   return;
    // }
    
    try (PreparedStatement pstm = con.prepareStatement("Select s.*,c.phoneNum,c.emailAddress,a.city,a.country,a.pincode from Student s join Address a on s.studentId = a.studentId join Contact c on s.studentId = c.studentId where s.studentId = ? ");) {
      pstm.setString(1, stId);
      ResultSet rs = pstm.executeQuery();
      if(!rs.next()){
        System.out.println("Student with studentId "+stId+" doesn't exist");
        return ;
      }
      
      String colsDesc = String.format("-%15s %30s %15s %30s %15s %15s %10s-","Student ID","Student Name", "Phone Number", "Email Address", "City", "Country", "Pincode");
      System.out.println(colsDesc);
      String val = String.format("-%15s %30s %15s %30s %15s %15s %10s-",rs.getString("StudentId" ), rs.getString("StudentName"),rs.getString("phoneNum"),rs.getString("emailAddress"),rs.getString("city"),rs.getString("country"),rs.getInt("pincode"));
      System.out.println(colsDesc);
      System.out.println("=".repeat(150));
      // System.out.println(st);
      System.out.println(val);
      System.out.println("=".repeat(150));
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    // toString method overrided inside studentimplementor
  }
  
  // how to print all student details? using loops?
  public void viewAllStudents(){
    // if(noStudentPresent()){
    //   System.out.println("=".repeat(150));
    //   System.out.println("No students data present");
    //   System.out.println("=".repeat(150));
    //   return;
    // }

    try {
      if(noStudentPresent()){
        System.out.println("No records of students present");
        return ;
      }
      PreparedStatement pstm = con.prepareStatement("Select s.*,c.phoneNum,c.emailAddress,a.city,a.country,a.pincode from Student s join Address a on s.studentId = a.studentId join Contact c on s.studentId = c.studentId");
      ResultSet rs = pstm.executeQuery();
      // if(!rs.next()){
      //   System.out.println("No student records are present");
      //   return ;
      // }
      String colsDesc = String.format("-%15s %30s %15s %30s %15s %15s %10s-","Student ID","Student Name", "Phone Number", "Email Address", "City", "Country", "Pincode");
      System.out.println(colsDesc);
      System.out.println("=".repeat(150));
      // for(Student st:stSet){
      //   System.out.println(st);
      // }
      while(rs.next()){
        System.out.println(String.format("-%15s %30s %15s %30s %15s %15s %10s-", rs.getString("studentId"),rs.getString("studentName"),rs.getString("phoneNum"),rs.getString("emailAddress"),rs.getString("city"),rs.getString("country"),rs.getInt("pincode")));
      }
      System.out.println("=".repeat(150));
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
  
  public void sortStudentsByName(){
    // if(noStudentPresent()){
    //   System.out.println("=".repeat(150));
    //   System.out.println("No students data present");
    //   System.out.println("=".repeat(150));
    //   return ; 
    // }
    // List<Student> stList = new ArrayList<>(stSet);
    try {
      // System.out.println(pass);
      if(noStudentPresent()){
        System.out.println("No records of students present");
        return ;
      }
      System.out.println("=".repeat(150));
      String colsDesc = String.format("-%15s %30s %15s %30s %15s %15s %10s-","Student ID","Student Name", "Phone Number", "Email Address", "City", "Country", "Pincode");
      System.out.println(colsDesc);
      // if(sortBy.equals("name"))
      //   sortBy = sortBy.replace(sortBy, "s.studentName");
      PreparedStatement pstm = this.con.prepareStatement("Select s.*,c.phoneNum,c.emailAddress,a.city,a.country,a.pincode from Student s join Address a on s.studentId = a.studentId join Contact c on s.studentId = c.studentId order by s.studentName;");
      // pstm.setString(1,"s.studentName");
      ResultSet rs = pstm.executeQuery();
      while(rs.next()){
        System.out.println(String.format("-%15s %30s %15s %30s %15s %15s %10s-", rs.getString("studentId"),rs.getString("studentName"),rs.getString("phoneNum"),rs.getString("emailAddress"),rs.getString("city"),rs.getString("country"),rs.getInt("pincode")));
      }
      System.out.println("=".repeat(150));
    } catch (Exception e) {
      // System.out.println(e.getMessage());
      e.printStackTrace();
    }

    // switch (sortBy.toLowerCase()) {
    //   case "id":{
    //     Collections.sort(stList,new sortComparatorById());
    //     break;
    //   }
    //   case "name":{
    //     Collections.sort(stList,new sortComparatorByName());
    //     break;
    //   }
    //   default:
    //     System.out.println("Incorrect field is given, only id and email are valid");
    //     break;
    //   }
  }


  public void sortStudentsById(){
    try {
      if(noStudentPresent()){
        System.out.println("No records of students present");
        return ;
      }
      System.out.println("=".repeat(150));
      String colsDesc = String.format("-%15s %30s %15s %30s %15s %15s %10s-","Student ID","Student Name", "Phone Number", "Email Address", "City", "Country", "Pincode");
      System.out.println(colsDesc);
      PreparedStatement pstm = this.con.prepareStatement("Select s.*,c.phoneNum,c.emailAddress,a.city,a.country,a.pincode from Student s join Address a on s.studentId = a.studentId join Contact c on s.studentId = c.studentId order by s.studentId;");
      ResultSet rs = pstm.executeQuery();
      while(rs.next()){
        System.out.println(String.format("-%15s %30s %15s %30s %15s %15s %10s-", rs.getString("studentId"),rs.getString("studentName"),rs.getString("phoneNum"),rs.getString("emailAddress"),rs.getString("city"),rs.getString("country"),rs.getInt("pincode")));
      }
      System.out.println("=".repeat(150));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private boolean isStudentExists(String stId) throws SQLException {
    PreparedStatement pstm = con.prepareStatement("select studentId from Student where studentId = ?");
    pstm.setString(1, stId);
    ResultSet rs = pstm.executeQuery();
    // pstm.close();
    return rs.next();
  }

  private boolean noStudentPresent() throws SQLException{
    PreparedStatement pstm = this.con.prepareStatement("select studentId from Student");
    ResultSet rs = pstm.executeQuery();
    return !rs.next();
    // pstm.close();
  }
}

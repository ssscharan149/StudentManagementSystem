package com.sms.manager;
import com.sms.model.*;
import com.sms.model.StudentImplementor.*;
import com.sms.utils.*;

import java.util.*;
public class StudentManager {
  private Set<Student> stSet;
  private ValidateEmailAndPhoneNumber v;
  public StudentManager(){
    this.stSet = new LinkedHashSet<>();
    this.v = new ValidateEmailAndPhoneNumber();
  }
  public void addNewStudent(String stId, String stName, String phn, String email, String city, String country, Long pincode ) throws InvalidStudentDetailsException{
    if(isStudentExists(stId)){
      System.out.println("=".repeat(150));
      System.out.println("Student with studentId "+stId+" already exists");
      System.out.println("=".repeat(150));
      return ;
    }
    Student st = new StudentImplementor();
    st.setStudentId(stId);
    st.setStudentName(stName);
    v.ValidatePhoneNumber(phn);
    v.ValidateEmail(email);
    st.setContactInfo(phn, email);
    st.setAddressInfo(city, country, pincode);
    stSet.add(st);
    System.out.println("=".repeat(150));
    System.out.println("Student with new studentId "+stId+" added successfully");
    System.out.println("=".repeat(150));
  }

  public void removeStudent(String stId){
    if(!isStudentExists(stId)){
      System.out.println("=".repeat(150));
      System.out.println("Student with studentId "+stId+" does not exist");
      System.out.println("=".repeat(150));
      return;
    }
    Student st = findStudent(stId);
    stSet.remove(st);
    System.out.println("=".repeat(150));
    System.out.println("Student with studentId "+stId+" removed successfully");
    System.out.println("=".repeat(150));
  }
  
  public void viewStudent(String stId){
    Student st = findStudent(stId);
    if(!isStudentExists(stId) || st.equals(null)){
      System.out.println("=".repeat(150));
      System.out.println("Student with studentId "+stId+" does not exist");
      System.out.println("=".repeat(150));
      return;
    }

    // toString method overrided inside studentimplementor
    String colsDesc = String.format("-%15s %30s %15s %30s %15s %15s %10s-","Student ID","Student Name", "Phone Number", "Email Address", "City", "Country", "Pincode");
    System.out.println(colsDesc);
    System.out.println("=".repeat(150));
    System.out.println(st);
    System.out.println("=".repeat(150));
  }
  
  // how to print all student details? using loops?
  public void viewAllStudents(){
    if(noStudentPresent()){
      System.out.println("=".repeat(150));
      System.out.println("No students data present");
      System.out.println("=".repeat(150));
      return;
    }
    String colsDesc = String.format("-%15s %30s %15s %30s %15s %15s %10s-","Student ID","Student Name", "Phone Number", "Email Address", "City", "Country", "Pincode");
    System.out.println(colsDesc);
    System.out.println("=".repeat(150));
    for(Student st:stSet){
      System.out.println(st);
    }
    System.out.println("=".repeat(150));
  }
  
  public void sortAndViewStudents(String sortBy){
    if(noStudentPresent()){
      System.out.println("=".repeat(150));
      System.out.println("No students data present");
      System.out.println("=".repeat(150));
      return ; 
    }
    List<Student> stList = new ArrayList<>(stSet);
    switch (sortBy.toLowerCase()) {
      case "id":{
        Collections.sort(stList,new sortComparatorById());
        break;
      }
      case "name":{
        Collections.sort(stList,new sortComparatorByName());
        break;
      }
      default:
        System.out.println("Incorrect field is given, only id and email are valid");
        break;
      }
      System.out.println("=".repeat(150));
    String colsDesc = String.format("-%15s %30s %15s %30s %15s %15s %10s-","Student ID","Student Name", "Phone Number", "Email Address", "City", "Country", "Pincode");
    System.out.println(colsDesc);
    for(Student st : stList){
      System.out.println(st);
    }
    System.out.println("=".repeat(150));
  }

  private boolean isStudentExists(String stId) {
    return stSet.stream().anyMatch(st -> st.getStudentId().equals(stId));
  }

  private Student findStudent(String stId){
    return stSet.stream().filter(st -> st.getStudentId().equals(stId)).findFirst().orElse(null);
  }

  private boolean noStudentPresent(){
    return stSet.isEmpty();
  }
}

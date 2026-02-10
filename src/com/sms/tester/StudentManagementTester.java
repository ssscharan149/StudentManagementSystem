package com.sms.tester;

import java.util.Scanner;
import com.sms.manager.*;
import com.sms.utils.*;


public class StudentManagementTester {
  public static void main(String[] args) {
    StudentManagerUsingDB st = new StudentManagerUsingDB();
    Scanner sc = new Scanner(System.in);
    while(true){
      System.out.println(".".repeat(150));
      System.out.println("Enter a choice");
      System.out.println("1. Add new student details");
      System.out.println("2. Remove student");
      System.out.println("3. View student by Id");
      System.out.println("4. View all students");
      System.out.println("5. Sort students by name and view");
      System.out.println("6. Sort students by id and view");
      System.out.println("0. Exit");
      System.out.println(".".repeat(150));
      int choice = sc.nextInt();
      sc.nextLine();
      
      switch (choice) {
        case 0:{
          sc.close();
          return ;
        }
        case 1:{
          System.out.println("Enter student details");
          System.out.println("Enter Id");
          String id = sc.nextLine();
          System.out.println("Enter Name");
          String name = sc.nextLine();
          System.out.println("Enter Phone Number");
          String phn = sc.nextLine();
          System.out.println("Enter Email");
          String email = sc.nextLine();
          System.out.println("Enter City");
          String city = sc.nextLine();
          System.out.println("Enter Country");
          String country = sc.nextLine();
          System.out.println("Enter Pincode");
          int pincode = sc.nextInt();
          sc.nextLine();
          try {
            st.addNewStudent(id,name,phn,email,city,country,pincode);
          } catch (InvalidStudentDetailsException e) {
            System.out.println(e.getMessage());
          }
          break;
        }
        case 2:{
          System.out.println("Enter the studentId which you want to remove");
          String stId = sc.nextLine();
          st.removeStudent(stId);
          break;
        }
        case 3:{
          System.out.println("Enter the studentId for viewing his details");
          String stId = sc.nextLine();
          st.viewStudent(stId);
          break;
        }
        case 4:{
          // System.out.println("Enter the studentId for viewing his details");
          // String stId = sc.next();
          st.viewAllStudents();
          break;
        }
        case 5:{
          st.sortStudentsByName();
          break;
        }
        case 6:{
          st.sortStudentsById();
          break;
        }
        default:
          break;
      }
    }
  }
}

package com.sms.model;

import java.util.Comparator;

public class StudentImplementor implements Student{

  private String studentId;
  private String studentName;
  private ContactInfo conInfo;
  private AddressInfo addInfo;

  @Override
  public String getStudentId() {
    return this.studentId;
  }

  @Override
  public void setStudentId(String Id) {
    this.studentId = Id;
  }

  @Override
  public String getStudentName() {
    return this.studentName;
  }

  @Override
  public void setStudentName(String name) {
    this.studentName = name;
  }

  @Override
  public String getContactInfo() {
    return this.conInfo.phoneNumber+" "+this.conInfo.email;
  }

  @Override
  public void setContactInfo(String phn, String email){
    this.conInfo = new ContactInfo();
    this.conInfo.phoneNumber = phn;
    this.conInfo.email = email; 
  }

  @Override
  public String getAddressInfo() {
    return this.addInfo.city+" "+this.addInfo.country+" "+String.valueOf(this.addInfo.pincode);
  }

  @Override
  public void setAddressInfo(String city, String country, int pincode) {
    this.addInfo = new AddressInfo();
    this.addInfo.city = city;
    this.addInfo.country = country;
    this.addInfo.pincode = pincode;
  }
  
  @Override
  public String toString(){
    return String.format("-%15s %30s %15s %30s %15s %15s %10s-",studentId, studentName, conInfo.phoneNumber, conInfo.email, addInfo.city, addInfo.country, addInfo.pincode);
  }

  public static class sortComparatorById implements Comparator<Student>{
    @Override
    public int compare(Student o1, Student o2) {
      return o1.getStudentId().compareTo(o2.getStudentId());
    }
  }

  public static class sortComparatorByName implements Comparator<Student>{
    @Override
    public int compare(Student o1, Student o2) {
      return o1.getStudentName().compareTo(o2.getStudentName());
    }
  }
}

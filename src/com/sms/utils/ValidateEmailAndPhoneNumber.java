package com.sms.utils;

public class ValidateEmailAndPhoneNumber {
  public void ValidateEmail(String email) throws InvalidStudentDetailsException{
    if(!email.contains("@"))
      throw new InvalidStudentDetailsException("Invalid email received, enter a valid email address with @ symbol");
  }
  public void ValidatePhoneNumber(String phoneNumber) throws InvalidStudentDetailsException{
    if(phoneNumber.length() != 10)
      throw new InvalidStudentDetailsException("Invalid phone number received, enter a valid phone number exactly 10 digits");
  }
}

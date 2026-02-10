package com.sms.model;
import java.sql.*;

public class StudentDb{
  public Connection getConnectionImp(String url, String user, String pass) throws SQLException, ClassNotFoundException{
    Class.forName("com.mysql.cj.jdbc.Driver");
    return DriverManager.getConnection(url,user,pass);
  }  
}

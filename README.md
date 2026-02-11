
# 🎓 Student Management System

### Java + JDBC + MySQL

## 📌 Project Overview

The **Student Management System (SMS)** is a console-based Java application that performs CRUD operations on student records using **JDBC** and **MySQL**.

This project demonstrates:

* ✅ Core Java (OOP principles)
* ✅ JDBC database connectivity
* ✅ MySQL relational database design
* ✅ Foreign key relationships
* ✅ PreparedStatement usage (SQL Injection prevention)
* ✅ Proper package structuring
* ✅ Debugging classpath and setup issues

It is designed as a backend-focused project to understand how Java applications interact with databases in real-world systems.

---

## 🏗 Project Architecture

```
com.sms
│
├── model
│     └── Student.java
│
├── manager
│     └── StudentManagerUsingDB.java
│
├── utils
│     └── ValidateEmailAndPhoneNumber.java
│
└── tester
      └── StudentManagementTester.java
```

### 🔹 Module Responsibilities

| Package | Responsibility                        |
| ------- | ------------------------------------- |
| model   | Entity classes (Student object)       |
| manager | Database operations (CRUD using JDBC) |
| utils   | Validation logic                      |
| tester  | Application entry point               |

---

## 🗄 Database Schema

### Database Name:

```
stdb
```

### Tables:

### 1️⃣ Student

```sql
CREATE TABLE Student(
  studentId VARCHAR PRIMARY KEY,
  studentName VARCHAR(50)
);
```

### 2️⃣ Address

```sql
CREATE TABLE Address(
  city VARCHAR(50),
  country VARCHAR(50),
  pincode VARCHAR(10),
  studentId INT,
  FOREIGN KEY (studentId)
  REFERENCES Student(studentId)
  ON DELETE CASCADE
);
```

### 3️⃣ Contact

```sql
CREATE TABLE Contact(
  phoneNum VARCHAR(15),
  emailAddress VARCHAR(100),
  studentId INT,
  FOREIGN KEY (studentId)
  REFERENCES Student(studentId)
  ON DELETE CASCADE
);
```

---

## 🚀 Features

* ➕ Add Student
* 📖 View Student Details
* ✏ Update Student
* ❌ Delete Student
* 🔗 Join Student + Address + Contact tables
* ✔ Email & Phone Validation
* 🔐 Secure DB password using environment variable

---

## ⚙️ Setup Instructions

---

### 1️⃣ Clone the Repository

```bash
git clone https://github.com/your-username/student-management-system.git
cd student-management-system
```

---

### 2️⃣ Install & Start MySQL

Create database:

```sql
CREATE DATABASE stdb;
USE stdb;
```

Create tables (use schema provided above).

---

### 3️⃣ Add MySQL JDBC Driver

Download:

```
mysql-connector-j-9.x.x.jar
```

Add it to:

* VS Code → Referenced Libraries
  OR
* Add to project classpath manually

---

### 4️⃣ Set Environment Variable for Password

The project uses:

```java
System.getenv("SQL_pass");
```

Set password:

#### Windows

```powershell
setx SQL_pass "your_mysql_password"
```

Restart IDE after setting.

---

### 5️⃣ Compile & Run

From src:

```bash
javac -cp ".;lib/mysql-connector-j-9.5.0.jar" -d bin com/sms/tester/StudentManagementTester.java
java -cp "bin;../lib/mysql-connector-j-9.5.0.jar" com.sms.tester.StudentManagementTester
```

## If package declaration fails
Solution is 
### Open Command Palette → Java: Configure Classpath
### Make sure src is listed as a source folder
or
### Right-click src → Add to Classpath

## If running the main class cause an error
While setting up, make sure to create launch.json and add classpaths inside it.

### add the below code inside it

com.example.Main → your actual main class
YourProjectName → project name shown in VS Code Explorer
`"configurations": [
    {
      "type": "java",
      "request": "launch",
      "mainClass": "com.example.Main",
      "projectName": "YourProjectName"
      "classPaths":["bin"]
    }
  ]`

  #### additionally you must create a bin folder, so that all the compiled class files are stored inside it, making vscode understand which classes to run and what is the main class.

### Go to the src folder:
Run this command, but make sure you are in src folder
Compile
### `javac -cp ".;lib/mysql-connector-j-9.5.0.jar" -d bin com/sms/tester/StudentManagementTester.java`
-d ../bin → put .class files in bin
run the main class which has main method
Run
### `java -cp "bin;../lib/mysql-connector-j-9.5.0.jar" com.sms.tester.StudentManagementTester`
---

# 🛠 Common Setup Errors & Solutions

---

## ❌ ClassNotFoundException: com.mysql.cj.jdbc.Driver

### Cause:

JDBC driver not added to classpath.

### Fix:

Add `mysql-connector-j.jar` to referenced libraries or compile using below command from src:

```bash
javac -cp ".;lib/mysql-connector-j-9.5.0.jar" -d bin com/sms/tester/StudentManagementTester.java
```

---

## ❌ package com.sms.manager does not exist

### Cause:

Folder structure does not match package declaration.

### Fix:

Ensure this structure:

```
com/sms/manager/
com/sms/model/
com/sms/utils/
com/sms/tester/
```

Compile from project root.

---

## ❌ Student.java is not on the classpath

### Cause:

File not placed inside correct package folder.

### Fix:

If file has:

```java
package com.sms.model;
```

It must be inside:

```
com/sms/model/
```

---

## ❌ Access denied for user 'root'

### Cause:

Wrong password or environment variable not set.

### Fix:

* Verify MySQL password
* Reset environment variable
* Restart IDE

---

## ❌ Foreign Key Error (Failed to open referenced table)

### Cause:

Child table created before parent table.

### Fix:

Create tables in this order:

1. Student
2. Contact
3. Address

---

## ❓ Why Query Results Appear in Ascending Order?

MySQL does not guarantee order unless specified.

Always use:

```sql
ORDER BY studentId;
```

---

# 🔐 Security Practices

* ✔ PreparedStatement (prevents SQL injection)
* ✔ Environment variable for DB password
* ✔ Foreign key constraints
* ✔ ON DELETE CASCADE for referential integrity

---

# 🎯 Learning Outcomes

This project helped in understanding:

* JDBC workflow
* Database connectivity
* Package structuring in Java
* Foreign key relationships
* Debugging classpath issues
* Handling real-world setup problems

---

# 📌 Future Improvements

* Convert to Spring Boot
* Add REST APIs
* Add React frontend
* Implement pagination
* Add logging framework
* Add unit testing



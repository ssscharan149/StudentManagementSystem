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
### `javac -d ../bin com/sms/manager/*.java com/sms/tester/*.java`
-d ../bin → put .class files in bin
Compile both manager and tester packages
### Run this command, but the current working directory (cwd) must be in bin folder
'cd ../bin`
`java com.sms.tester.StudentManagementTester`
run the main class which has main method





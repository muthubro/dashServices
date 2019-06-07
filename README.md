# dashServices
REST APIs for Dash Desk


## How to run
- **In Spring Tool Suite**: Do **Right Click > Run As > Spring Boot Application**
- **Using command line**:
   1. Change the directory to your project location using **cd *project_location***
   2. Run **mvn spring-boot:run**
 
 
## Project Structure
- Java: All java files go here
  - Config: All project configuration files
  - Controller: All controllers
  - Model: All models
  - Payload: Request and response structures
  - Repository: Spring Data Repositories (Mongo and JPA)
  - Security: All security related files
  - Service: Services for the controllers
  - Utility: Utilities used throughout the project
  
- Resources: Everything else goes here
  - Homework: The homework files uploaded are saved here
  - Static: All static resources
  - Templates: HTML templates
  - *application.properties*: Project configurations and application wide properties
  
 
 ## Database Models
 - MongoDB: Attendance, Homework, Student
 - MySQL: Admin

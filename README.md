# dashServices
The REST APIs for multiple Dash applications including Dash Desk and Dash App.

 ## Prerequisites
 - Spring Tool Suite (STS 4) or Eclipse IDE
 - MongoDB
 - MySQL
 - Tomcat (Ver 9.0)
 
## Setup Instructions
- Assumes you have STS 4 installed on your system
- Assumes you have both MongoDB and MySQL installed
- Create a database named 'dash' in both of MongoDB and MySQL. Find the details in dashServices/src/main/resources/application.properties
- **In Spring Tool Suite** Go to File > Import > Maven > Existing Maven Projects and select the cloned repo.
- The previous step would have added a project 'dashServices' on to your STS.
- To do download all the maven dependencies mentioned in the project POM.xml, right click on the Project and then go to, Run As > Maven Install
- If the previous step completed successfully, you have all the dependencies installed correctly.
- Add a Tomcat Server (See how - http://doraprojects.net/blog/?p=3330)

## To Run
- **Using Spring Tool Suite**: After Right Click on the Project, Go to Run As > Spring Boot Application
- **Using command line**:
   1. Change the directory to your project location using **cd *project_location***
   2. Run **mvn spring-boot:run**
 
 
## Project Structure
  - Config: All project configuration files
  - Controller: All controllers
  - Model: All models
  - Payload: Request and response structures
  - Repository: Spring Data Repositories (Mongo and JPA)
  - Security: All security related files
  - Service: Services for the controllers
  - Utility: Utilities used throughout the project
  
- Resources:
  - Homework: The homework files uploaded are saved here
  - Static: All static resources
  - Templates: HTML templates
  - *application.properties*: Project configurations and application wide properties
  
 
 ## Miscellaneous
 **Database Models**
 - MongoDB: Attendance, Homework, Student
 - MySQL: Admin

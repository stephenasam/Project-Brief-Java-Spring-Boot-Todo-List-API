Project Summary 

Purpose and Scope: The Todo List API is a RESTful API built with Spring Boot that allows users to manage a list of todo items. It provides CRUD (Create, Read, Update, Delete) operations for todo items, which are persisted in an SQLite database. 

Technology Stack: 

- Java 
- Spring Boot 
- Spring MVC 
- SQLite 
- JDBC 
- JUnit 
- Mockito 

Design Considerations: The project follows a layered architecture with a clear separation of concerns: 

- The Controller layer handles the REST API endpoints and input validation. 
- The Service layer contains the business logic for managing todo items. 
- The Repository layer interacts with the SQLite database using JDBC and SQL. 

The project also adheres to the principles of Test-Driven Development (TDD), with thorough unit tests and integration tests for the controllers, services, and repositories. 

In-depth Instructions 

Environment Setup: To run the Todo List API project, you'll need the following prerequisites: 

- Java Development Kit (JDK) 17 or later 
- Apache Maven 

Building the Application: 

1. Clone the project repository: git clone https://github.com/your- username/todo-list-api.git
1. Navigate to the project directory:  
1. Build the project using Maven: ./mvnw clean install

Running the Application: 

1. After the build is successful, you can run the application using the following command: ./mvnw spring-boot:run
1. The application will start on http://localhost:8080. 

Swagger API Documentation: 
- http://localhost:8080/swagger-ui/index.html#

Testing the Application: 

Running Tests: To run the unit and integration tests, use the following Maven command: 

./mvnw test

Understanding Test Output: The test output will display the number of tests executed, along with any failures or errors. If all tests pass, you should see a message like BUILD SUCCESS. 

Using the API: API Endpoints: 

- GET /api/todos: Retrieve a list of all todo items. 
- GET /api/todos/{id}: Retrieve a specific todo item by its ID. 
- POST /api/todos: Create a new todo item. 
- PATCH /api/todos/{id}: Update an existing todo item by its ID. 
- DELETE /api/todos/{id}: Delete a todo item by its ID. 

Postman Collection: A Postman collection is available in the postman_collection directory of the project. You can import this collection into Postman to explore the API endpoints and send requests with sample data. 

Additional Considerations Troubleshooting Guide: 

- If you encounter any issues with the SQLite database, ensure that the todo.db file exists in the project directory. If not, the application will create a new database file on startup. 
- If you receive an error related to the SQLite dialect, ensure that the sqlite-jdbc dependency is added to the pom.xml file as mentioned in the environment setup. 
- If the tests fail, double-check the test output for any specific error messages and ensure that the test data and expectations are correct. 

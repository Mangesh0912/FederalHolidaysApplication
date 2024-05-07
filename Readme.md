Federal Holidays API

Description
This project provides a RESTful API to manage and retrieve federal holidays for specified countries, initially supporting the USA and Canada.
It allows users to add, update, and list holidays. 
The application is built using Spring Boot and utilizes an H2 in-memory database for simplicity and ease of setup.

Features:
Add Holidays: Users can add new federal holidays to the system.
Update Holidays: Existing holiday details can be updated.
List Holidays: Users can retrieve holidays by country.

Installation
Prerequisites:
Java JDK 11 or later
Maven 3.6 or later
Git

Getting Started:
Clone the repo: git clone https://github.com/yourusername/federalholidays.git
Navigate to project folder in cmd.
Build the project -> mvn clean install
Run the application -> mvn spring-boot:run
Postman Collections -> These are also added to the project please import them into your Postman UI.
Swagger UI -> Access Swagger UI at http://localhost:8080/swagger-ui/index.html
Testing -> Run Tests using Maven : mvn test.


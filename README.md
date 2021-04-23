This is a REST API built on Java-Springboot that exposes an API catalog by a Swagger UI. 

All data is stored in an H2DB which looses information each time the application is terminated.

Run the application with maven and go to http://localhost:8080/swagger-ui.html in order to access API documentation.

This API provides two main models : User and Account

The API allows:

-Create new user

-Create new account

-List all users

-List all accounts

-Get user details by it's Id

-Get account details by it's Id

-Bind an account to a user

-Unbind account from user 

Document for Library Management System
This is a simple library management system that can handle the operation of managing books, patrons, and borrowing books.
In books and patrons, the user can do the basic crud operations such as create, view, update, and delete.
In the borrowing books APIs, the user will be able to borrow a book and save it in the database, once returned the user can update the status of the borrowed book by changing the return date to the time of managing the process.

The System is using SQL server as a database, however it can be changed and managed by the application.properties file where you can use any other database since the project is a code first project and the entities will be created once the connection is done.
The System is also having basic unit test as per requested.
The System is also using Transactional for managing the completion of the basic process and using caching to speed up the process.
The System is also using AOP to handle the loggers on all the controllers to be checked.
You will be also able to test all the APIs using Swagger by running the project and accessing the following link: http://localhost:2424/swagger-ui/index.html#/

Please do not hesitate to send any questions regarding the code or anything else.


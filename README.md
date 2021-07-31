# library-management-system
A simple project to host online library.

It provides below functionalities:


Account Management System
  1. Register a new account
  2. Login to an existing account


Book Management System
  1. Add a new book
  2. Add a book copy
  3. Update an existing book
  4. Update an existing book copy


Book Lending System
  1. Issue a book to a member
  2. Return a book
  3. Fetch books issued by a member


Catalogue System
  1. Fetch all books
  2. Fetch books by title
  3. Fetch books by author
  4. Fetch books by subject
  5. Fetch books by language

All the functionalities are exposed using REST APIs and are well integrated with Swagger2 UI. This can be found here: http://localhost:8080/swagger-ui.html.

The service doesn't host any DB but stores all the data in memory. This is done by purpose to keep things focussed on OOPs.
It is created using Spring Boot 2.5.3

Service is designed and implemented keeping in mind java best practices. It follows MVC architecture except model and data transfer objects are kept same owing to absence of DB. Exception handling is done to take care of undesired inputs/outputs.

The request validation over different APIs is not working currently. This will be looked into and ressolved as time permits.

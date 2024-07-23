# Event Management System

## Introduction

This project is a Event Management System developed during my course at [**Future Training & Consulting GmbH**](https://www.futuretrainings.com/) 
in the [**Java Hibernate**](https://www.futuretrainings.com/trainings/arbeitssuchende/programmierung/programmiersprachen/ls23.220/java-hibernate/) course. 
The system allows for managing concerts and purchasing tickets for these events.

## Project Recognition

This project was highlighted by the course mentor and selected as an example of a well-executed project for future students of the course.

## Technologies Used

- **Spring Boot**: For building the backend application.
- **JPA (Java Persistence API)**: For object-relational mapping.
- **Hibernate**: As the JPA implementation.
- **H2DB**: In-memory database for storing data.
- **JCache**: As an abstraction for Second Level Caching.
- **EhCache**: As the implementation for JCache.
- **Thymeleaf**: For server-side rendering of HTML.

## Installation

1. **Clone the repository**:
    ```bash
    git clone https://github.com/AlexanderHantel/event-manager-jpa.git
    cd event-manager
    ```

2. **Build the project**:
    ```bash
    ./mvnw clean install
    ```

3. **Run the application**:
    ```bash
    ./mvnw spring-boot:run
    ```

4. **Access the application**:
    Open your browser and go to `http://localhost:8080`

## Database

The application uses an H2 in-memory database. The database is automatically created and populated with some initial data on application startup, that were declared in file [**data.sql**](https://github.com/AlexanderHantel/event-manager/blob/master/src/main/resources/data.sql). 
To view and interact with the database, access the H2 console:

1. **Open the H2 Console**: Go to `http://localhost:8080/h2-console`
2. **JDBC URL**: `jdbc:h2:mem:testdb`
3. **Username**: `sa`
4. **Password**: (leave blank)

## Contact

For any questions or feedback, please contact me at [alek.hantel@gmx.de].

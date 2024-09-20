# Compound Interest Calculator

![msedge_FN3ZtEzQQl](https://github.com/user-attachments/assets/dbec7482-a432-4989-a02d-e02bea81fa52)

## Reason for Project

I wanted to expand my knowledge of Java related technology and API's so I started learning what I could and this project is me puttting everything I've learned so far to use.

### Disclaimer

This project is meant to have an amalgamation of all the different things I've been learning so it's not meant to be very efficient in general.  


## Overview

This project is a web application for calculating compound interest and managing calculation history. It supports both XML and JSON formats for retrieving historical calculation data.

## Features

- **Compound Interest Calculation**: Calculate compound interest based on user input for principal, interest rate, number of years, and number of times interest is compounded per year.
  
- **Calculation History**: Store and manage historical calculations with details such as principal, interest rate, years, compounding frequency, and result. 

- **View History**: Access calculation history in both JSON and XML formats through RESTful endpoints.

- **Interactive User Interface**: User-friendly web interface (index.jsp) for inputting values and viewing results.

- **Data Persistence**: Uses MySQL for storing calculation history, ensuring data is saved across sessions.

- **Form Validation**: Includes input validation to ensure that all required fields are filled before performing calculations.

- **Data Management**: Options to view or clear the history of calculations directly from the user interface.

- **RESTful API**: Exposes RESTful endpoints for retrieving history in different formats and managing calculation history programmatically.


## Technologies Used

- **Java**
- **Jakarta EE**
- **JSP (JavaServer Pages)**
- **Servlet API**
- **JDBC (Java Database Connectivity)**
- **MySQL**
- **RESTful Web Services**
- **XML**
- **HTML/CSS**
- **Maven**
- **MySQL Connector/J**

## Getting Started

### Prerequisites

- Java 11 or higher
- MySQL Database

### Configuration

1. **Database Configuration**: Update `CalcDAO` with your database credentials.

   ```java
   private static String url = "jdbc:mysql://localhost:3306/calc_history";
   private static String userName = "root";
   private static String passWord = "root";
   ```

2. **Create Database**: Ensure the database schema `calc_history` exists and has the table `PrevResults` with appropriate columns.


## Usage

1. **Calculate Compound Interest**

   - Navigate to `http://localhost:8080/Calculate`
   - Submit the form with the following fields:
     - `principle` - The principal amount for the calculation.
     - `interest` - The annual interest rate (as a percentage).
     - `years` - The number of years for the investment.
     - `perYear` - The number of times interest is compounded per year.
     - `history` - Optional boolean value to show calculation history (`true`/`false`).
     - `delete` - Optional boolean value to clear the calculation history (`true`/`false`).

2. **View Calculation History**

   - To retrieve the history of calculations in JSON format, use the endpoint:
     - **GET** `/HistoryResource/JSON`
   
   - To retrieve the history of calculations in XML format, use the endpoint:
     - **GET** `/HistoryResource/XML`
    
## API Endpoints

### History Endpoints

- **Get History (JSON)**
  - **GET** `/HistoryResource/JSON`
  Retrieves the calculation history in JSON format.

- **Get History (XML)**
  - **GET** `/HistoryResource/XML`
  Retrieves the calculation history in XML format.
    
## Project Structure

```bash
src/main/java/com/example/comp_interest_calc
├── CalcDAO.java               # Manages database connection and credentials
├── CalcServlet.java           # Handles HTTP requests and responses
├── HistoryItem.java           # Defines the entity for calculation history
├── HistoryItemRepo.java       # Manages history items in the database
├── HistoryResource.java       # Provides RESTful endpoints for history
└── Utils.java                 # Utility class for calculation and validation

src/main/webapp
└── ErrorPages                 # Error Pages
    ├── Error_404.jsp                
    ├── Error_500.jsp
    └── Exception_Parse.jsp
└── WEB-INF
    └── web.xml                # Deployment descriptor for the web application
├── index.jsp                  # Main JSP file for user interface
└── style.css                  # Main CSS file for page design

```

## Code Explanation

### `CalcDAO`

- Manages the connection to the MySQL database.
- Provides methods for updating database credentials and establishing a connection.
- Contains a static block to load the MySQL JDBC driver.

### `CalcServlet`

- Handles HTTP requests for calculating compound interest and displaying results.
- **`doGet`**: Displays the `index.jsp` page when accessed directly.
- **`doPost`**: Processes the form submission, calculates interest, and manages history.

### `HistoryItem`

- Represents a record of a compound interest calculation.
- Contains fields for the principal, interest rate, years, compounding frequency, and result.
- Includes methods for getting and setting these fields, as well as XML serialization.

### `HistoryItemRepo`

- Manages CRUD operations for historical calculation records in the database.
- **`insertItem`**: Inserts a new calculation record into the database.
- **`getItems`**: Retrieves all calculation records from the database and returns them as a `HashMap`.
- **`clearHistory`**: Clears the calculation history by dropping and recreating the `PrevResults` table.

### `HistoryResource`

- Provides RESTful endpoints for retrieving calculation history.
- **`getHistoryJSON`**: Returns calculation history in JSON format.
- **`getHistoryXML`**: Returns calculation history in XML format.

### `Utils`

- Contains utility methods for interest calculation and input validation.
- **`calculateInterest`**: Parses input values, performs the compound interest calculation, and stores the result in the database.
- **`checkInput`**: Validates that the input fields are not empty or null.


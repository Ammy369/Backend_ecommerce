# eCommerce Project

This is an eCommerce project with a frontend built in Angular 17 and a backend built in Spring 3.3.0. The project is structured into two Develop parts:

- `fe-ecommerce` (Frontend: Angular 17)
- `be-ecommerce` (Backend: Spring 3.3.0)

## Table of Contents

- [eCommerce Project](#ecommerce-project)
  - [Table of Contents](#table-of-contents)
  - [Installation](#installation)
  - [Backend Setup](#backend-setup)
    - [Prerequisites](#prerequisites)
    - [Configure MySQL Database](#configure-mysql-database)
    - [Build and Run the Backend](#build-and-run-the-backend)
  - [Frontend Setup](#frontend-setup)
    - [Prerequisites](#prerequisites-1)
    - [Install Dependencies](#install-dependencies)
    - [Build and Run the Frontend](#build-and-run-the-frontend)
    - [Production Build](#production-build)
  - [Running the Application](#running-the-application)
  - [Technologies Used](#technologies-used)
  - [Contributing](#contributing)

## Installation

Clone the repository to your local machine:

```sh
git clone https://github.com/your-username/ecommerce-project.git
cd ecommerce-project
```

## Backend Setup
Navigate to the backend project directory:

```sh
cd be-ecommerce
```

### Prerequisites
- Java 21
- Maven
- MySQL

### Configure MySQL Database
Create a MySQL database for the project and update the application.properties file with your database configuration.

### Build and Run the Backend
1. Build the project using Maven:

```sh
mvn clean install
```

2. Run the Spring Boot application:

```sh
mvn spring-boot:run
```

The backend server will start on http://localhost:8080.

##  Frontend Setup
Navigate to the frontend project directory:

```sh
cd fe-ecommerce
```
### Prerequisites
- Node.js (version 16 or higher)
- Angular CLI
### Install Dependencies
Install the project dependencies using npm:

```sh
npm install
```
### Build and Run the Frontend
1. To serve the Angular application in development mode:

```sh
ng serve
```
2. The application will run on http://localhost:4200.

### Production Build
To create a production build of the application:

```sh
ng build --configuration production
```
## Running the Application
Ensure both the backend and frontend servers are running:

1. Start the backend server on http://localhost:8080.
2. Start the frontend server on http://localhost:4200.
Navigate to http://localhost:4200 to access the eCommerce application.

## Technologies Used
- Frontend: Angular 17, Bootstrap, SCSS
- Backend: Spring Boot 3.3.0, Spring Data JPA, Spring Data REST, MySQL
- Build Tools: Maven (Backend), Angular CLI (Frontend)
## Contributing
Contributions are welcome! Please fork the repository and create a pull request with your changes. Ensure that your code follows the project's coding standards and passes all tests.

1. Fork the repository
2. Create a new branch (git checkout -b feature/YourFeature)
3. Commit your changes (git commit -am 'Add some feature')
4. Push to the branch (git push origin feature/YourFeature)
5. Create a new Pull Request


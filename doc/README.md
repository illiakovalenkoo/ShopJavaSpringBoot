# Shop2 Web Application

## Overview
Shop2 is a **Spring Boot** web application that provides a basic e-commerce functionality. The project includes:
- **User authentication** (Spring Security)
- **Product management** (add, update, delete items)
- **Database storage** (MySQL with Hibernate ORM)
- **Thymeleaf-based UI** for frontend rendering

## Requirements
Before running the project, ensure you have installed:
- **Java 17+** (Tested with OpenJDK 23.0.2)
- **IntelliJ IDEA** (Recommended for running the project)
- **MAMP/XAMPP** or MySQL Server (Database needs to be running)
- **Maven** (To handle dependencies)

## Setup & Running in IntelliJ IDEA
1. **Clone the repository or extract the project folder**
   ```sh
   git clone <repository-url>
   cd shop2
   ```
2. **Open the project in IntelliJ IDEA**
   - Go to **File** -> **Open** -> Select the `shop2` project folder.

3. **Check and Configure Database Connection**
   - Open `src/main/resources/application.properties`
   - Ensure the database credentials are correctly set:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/spring2_db?useUnicode=true&serverTimezone=UTC
     spring.datasource.username=root
     spring.datasource.password=root
     spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
     ```
   - If using **MAMP/XAMPP**, start your MySQL server manually.

4. **Run the Application**
   - In IntelliJ, go to `Shop2Application.java`
   - Right-click -> **Run 'Shop2Application'**
   - The server should start at `http://localhost:8008/`

## Features
- **User Management**: Secure login with Spring Security.
- **Product Management**: Add, update, and delete products.
- **Thymeleaf UI**: Dynamic HTML pages using Thymeleaf templates.
- **Database Storage**: Products and user data are stored in a MySQL database.

## Troubleshooting
### Database Connection Issues
- Ensure MySQL is running on **port 3306** and the database `spring2_db` exists.
- Use `mysql -u root -p` to check your MySQL setup.
- Modify `application.properties` if your MySQL credentials are different.

### Java Version Issues
- Ensure you are running **Java 17 or newer**.
- Check Java version with:
  ```sh
  java -version
  ```

## License
This project is open-source and free to use.

# Introduction to JDBC

## Learning Goals

- Define Java Database Connectivity (JDBC)
- Register a JDBC driver in a Java project
- Execute a Java program to connect to a PostgreSQL database

## Introduction

**Java Database Connectivity (JDBC)** is an API that defines Java interfaces and classes
for connecting to a database and executing SQL statements.  

**JDBC** allows a Java program to access different database management
systems. A **JDBC** driver implements the API for a specific DBMS
vendor, such as PostgreSQL.

## Code along

[Fork and clone](https://github.com/learn-co-curriculum/java-mod-5-jdbc-intro) this lesson to run the sample Java programs.

## Create a new database

We connect to a database by registering the JDBC driver
and then opening a database connection in a Java program.
The Java program will use the driver to connect to a specific database.

We will create a new database named `employee_db` for the lessons in this section.

1. Open the PostgreSQL **pgAdmin** tool.
2. Create a new database `employee_db`.

![new employee database](https://curriculum-content.s3.amazonaws.com/6036/introduction-to-jdbc/new_database.png)

## Register the JDBC driver

While there are various ways to register the driver,
we will simply add a dependency in the Maven configuration file `pom.xml`.  

Open `pom.xml` to confirm the PostgreSQL driver dependency exists.
If not, copy the dependency and press the IntelliJ icon to load the Maven changes.

```text
<dependencies>
    <!-- https://mvnrepository.com/artifact/org.postgresql/postgresql -->
    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
        <version>42.5.0</version>
    </dependency>
</dependencies>  
```


## Connect to the database through the JDBC driver

Once the database exists, we can run the example program `ConnectionTest`
to test if the PostgreSQL driver can get a connection to the database.
The program relies on the following JDBC class and interface:

- **DriverManager** : A class that provides basic services for managing database drivers.
- **Connection** : An interface that represents a connection (session) with a specific database
  that can execute SQL statements.

We use the `DriverManager.getConnection()` method to open a connection to the database.
This method requires a connection URL String parameter, along with a userid and password.
The method returns a new `Connection` object, which can be used to execute queries.

Run the `ConnectionTest` class and confirm the successful connection message is printed.

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {

  static final String DB_URL = "jdbc:postgresql://localhost:5432/employee_db";
  static final String USER = "postgres";
  static final String PASSWORD = "postgres";

  public static void main(String[] args) {
    //Establish a connection to the database
    try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
      System.out.println("Connected to database");
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }
}
```

The method call `DriverManager.getConnection` may throw an exception if there is an issue
connecting to the database.  Notice the code uses a `try-with-resources` statement,
which means the database connection will automatically be closed after the
print statement executes.

If the program throws an exception, check that the user, password, and database name are correct
and that the database exists.


## Conclusion

In this lesson we used the JDBC API to run a Java program that connects to an existing
PostgreSQL database.

## Resources

- [JDBC API](https://docs.oracle.com/javase/8/docs/api/java/sql/package-summary.html)    
- [PostgreSQL JDBC Driver](https://jdbc.postgresql.org/download/)    
- [JDBC Tutorial](https://docs.oracle.com/javase/tutorial/jdbc/basics/index.html)   
- [java.sql.DriverManager](https://docs.oracle.com/en/java/javase/11/docs/api/java.sql/java/sql/DriverManager.html)  
- [java.sql.Connection](https://docs.oracle.com/en/java/javase/11/docs/api/java.sql/java/sql/Connection.html)   

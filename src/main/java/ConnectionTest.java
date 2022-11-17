import java.sql.*;

public class ConnectionTest {

    static final String DB_URL = "jdbc:postgresql://localhost:5432/employee_db";
    static final String USER = "postgres";
    static final String PASSWORD = "postgres";

    public static void main(String[] args) {
        try {
            //Establish a connection to the database
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            System.out.println("Connected to database");

            //Close the connection
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
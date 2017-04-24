package JDBC;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String URL = "jdbc:mysql://localhost:3306/mysql";

    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        Driver driver;

        try {
            driver = new com.mysql.jdbc.Driver();       //download driver
            DriverManager.registerDriver(driver);       //registered driver
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            System.out.println("Realization...");

        } catch (SQLException ex) {
            System.out.println("Error!");
            return;
        } finally {
            if (connection != null)
                connection.close();
        }


    }
}

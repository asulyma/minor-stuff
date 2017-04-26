package JDBC;

import java.sql.*;

public class Main {
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String URL = "jdbc:mysql://localhost:3306/mysql?useSSL=false";

    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        Driver driver;
        Statement statement;

        try {
            driver = new com.mysql.jdbc.Driver();       //download driver
            DriverManager.registerDriver(driver);       //registered driver
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.createStatement();   //create statement for commands

            //statement.execute("INSERT INTO business.products (product_name, price, shop_id) VALUES (\"Sosages\", 44,2)");
            //statement.executeUpdate("UPDATE business.products SET product_name=\"Snickers\" WHERE product_id=7");
            //ResultSet resultSet = statement.executeQuery("SELECT * FROM business.products");


        } catch (SQLException ex) {
            System.out.println("Error!");
            return;
        } finally {
            if (connection != null)
                connection.close();
        }


    }
}

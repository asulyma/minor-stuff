package JDBC;

import java.sql.*;

public class Main {
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String URL = "jdbc:mysql://localhost:3306/mysql?useSSL=false";

    public static void main(String[] args) throws SQLException {
        int tmpID, tmpShopID;
        String tmpName;
        double tmpPrice;

        Connection connection = new DBProcessor().getConnection(URL,USERNAME,PASSWORD);
        String query = "select * from business.products";
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()){
            tmpID = resultSet.getInt("product_id");
            tmpName = resultSet.getString("product_name");
            tmpShopID = resultSet.getInt("shop_id");
            tmpPrice = resultSet.getDouble("price");
            Product product = new Product(tmpID, tmpName,tmpPrice,tmpShopID);
            System.out.println(product);
        }

        statement.close();
        connection.close();
    }
}

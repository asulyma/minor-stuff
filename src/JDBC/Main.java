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

        Connection connection = new DBProcessor().getConnection(URL, USERNAME, PASSWORD);
        Statement statement = connection.createStatement();

        String query = "select * from business.products";
        PreparedStatement preparedQuery = connection.prepareStatement(query);

        String insert = "insert into business.products (product_name, price, shop_id) values (?, ?, ?)";
        PreparedStatement preparedInsert = connection.prepareStatement(insert);

        preparedInsert.setString(1, "PreparedFood");
        preparedInsert.setDouble(2, 98.9);
        preparedInsert.setInt(3, 2);
        preparedInsert.execute();


        ResultSet resultSet = preparedQuery.executeQuery(query);
        while (resultSet.next()) {
            tmpID = resultSet.getInt("product_id");
            tmpName = resultSet.getString("product_name");
            tmpShopID = resultSet.getInt("shop_id");
            tmpPrice = resultSet.getDouble("price");
            Product product = new Product(tmpID, tmpName, tmpPrice, tmpShopID);
            System.out.println(product);
        }

        preparedQuery.close();
        connection.close();
    }
}

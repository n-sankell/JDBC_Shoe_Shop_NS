package dbconnection;

import gui.CustomJop;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class RepositoryAddToCart {

    private final String name;
    private final String password;

    public RepositoryAddToCart(Properties properties) {
        this.name = properties.getProperty("name");
        this.password = properties.getProperty("password");
    }

    public String addToNewCart(int customerId, int orderId, int shoeId) {
        String message = "";
        Connection connection;
        CallableStatement callableStatement;
        ResultSet resultSet;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shoe_shop_db_new", name, password);
            callableStatement = connection.prepareCall("CALL shoe_shop_db_new.addToCart(?,?,?)");
            callableStatement.setInt(1,customerId);
            callableStatement.setInt(2,orderId);
            callableStatement.setInt(3,shoeId);
            callableStatement.execute();
            System.out.println("newCart");
            System.out.println("Customer: "+customerId +" Shoe: "+shoeId);

            resultSet = callableStatement.getResultSet();
            while (resultSet.next()) {
                message = resultSet.getString("complete");
            }
            resultSet.close();
            callableStatement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return message;
    }

    private int getLastIndex() {
        List<Integer> allOrderIds = new ArrayList<>();
        int lastIndex = 0;
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shoe_shop_db_new", name, password)) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id FROM shoe_shop_db_new.shoe_order");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                allOrderIds.add(resultSet.getInt("id"));
            }
            lastIndex = Collections.max(allOrderIds);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("lastIndex: "+lastIndex);
        return lastIndex;
    }

    public String addToExistingCart(int customerId, int shoeId) {
        int lastIndex = getLastIndex();
        String message = "";
        Connection connection;
        CallableStatement callableStatement;
        ResultSet resultSet;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shoe_shop_db_new", name, password);
            callableStatement = connection.prepareCall("CALL shoe_shop_db_new.addToCart(?,?,?)");
            callableStatement.setInt(1,customerId);
            callableStatement.setInt(2,lastIndex);
            callableStatement.setInt(3,shoeId);
            callableStatement.execute();
            System.out.println("Existing Cart");
            System.out.println("Customer: "+customerId +" Shoe: "+shoeId);

            resultSet = callableStatement.getResultSet();
            while (resultSet.next()) {
                message = resultSet.getString("complete");
            }
            resultSet.close();
            callableStatement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return message;
    }

}

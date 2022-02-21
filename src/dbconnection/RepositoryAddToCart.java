package dbconnection;

import java.sql.*;
import java.util.Properties;

public class RepositoryAddToCart {

    private final String name;
    private final String password;

    public RepositoryAddToCart(Properties properties) {
        this.name = properties.getProperty("name");
        this.password = properties.getProperty("password");
    }

    public void addToNewCart(int customerId, int orderId, int shoeId) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shoe_shop_db_new", name, password)) {
            CallableStatement callableStatement = connection.prepareCall("CALL shoe_shop_db_new.addToCart(?,?,?)");
            callableStatement.setInt(1,customerId);
            callableStatement.setInt(2,orderId);
            callableStatement.setInt(3,shoeId);
            callableStatement.execute();
            System.out.println("newCart");
            System.out.println("Customer: "+customerId +" Shoe: "+shoeId);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private int getLastIndex() {
        int lastIndex = 0;
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shoe_shop_db_new", name, password)) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id FROM shoe_shop_db_new.shoe_order");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                lastIndex = resultSet.getInt("id");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lastIndex;
    }

    public void addToExistingCart(int customerId, int shoeId) {
        int lastIndex = getLastIndex();
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shoe_shop_db_new", name, password)) {

            CallableStatement callableStatement = connection.prepareCall("CALL shoe_shop_db_new.addToCart(?,?,?)");
            callableStatement.setInt(1,customerId);
            callableStatement.setInt(2,lastIndex);
            callableStatement.setInt(3,shoeId);
            callableStatement.execute();
            System.out.println("Existing Cart");
            System.out.println("Customer: "+customerId +" Shoe: "+shoeId);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}

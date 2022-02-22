package dbconnection;

import dbobjectmodel.Area;
import dbobjectmodel.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class RepositoryFindCustomer {

    private final List<Customer> allCustomers = new ArrayList<>();
    private final String name;
    private final String password;

    public RepositoryFindCustomer(Properties properties) {
        this.name = properties.getProperty("name");
        this.password = properties.getProperty("password");
    }

    public void fetchCustomersToList() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shoe_shop_db_new", name, password)) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM shoe_shop_db_new.customer");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int areaId = resultSet.getInt("areaId");
                String customerName = resultSet.getString("fullName");
                String customerPassword = resultSet.getString("password");
                Customer customer = new Customer(id, areaId, customerName, customerPassword);
                customer.setArea(getAreaFromCustomerId(id));
                allCustomers.add(customer);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private Area getAreaFromCustomerId(int customerId) {
        Area area = null;
        String query = "SELECT * FROM shoe_shop_db_new.postal_area inner join " +
                "shoe_shop_db_new.customer on customer.areaId = postal_area.id where customer.id = ?";
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shoe_shop_db_new", name, password);
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1,customerId+"");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                area = new Area(resultSet.getInt("id"),resultSet.getString("areaName"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return area;
    }

    public List<Customer> getCustomers() {
        return allCustomers;
    }


}

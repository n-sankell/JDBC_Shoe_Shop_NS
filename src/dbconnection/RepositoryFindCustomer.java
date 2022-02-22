package dbconnection;

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
                int id = resultSet.getInt("customer.id");
                int areaId = resultSet.getInt("customer.areaId");
                String customerName = resultSet.getString("customer.fullName");
                String customerPassword = resultSet.getString("customer.password");
                Customer customer = new Customer(id, areaId, customerName, customerPassword);
                allCustomers.add(customer);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }



    public List<Customer> getCustomers() {
        return allCustomers;
    }


}

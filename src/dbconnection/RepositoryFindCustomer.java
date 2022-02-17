package dbconnection;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class RepositoryFindCustomer {

    public void getAllCustomers(Properties properties) {
        String name = properties.getProperty("name");
        String password = properties.getProperty("password");
        String connectionString = properties.getProperty("connectionString");
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shoe_shop_db_new", name, password)) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM shoe_shop_db_new.customer");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("customer.id");
                int areaId = resultSet.getInt("customer.areaId");
                String customerName = resultSet.getString("customer.fullName");
                String customerPassword = resultSet.getString("customer.password");
                System.out.printf("ID: %s Name: %s AreaId: %s Password: %s\n",id,customerName,areaId,customerPassword);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }



    private int parseInputToInt() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Could not parse integer.");
            }
        }
    }

}

package dbconnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class RepositoryAddToCart {

    private final Properties properties;

    public RepositoryAddToCart(Properties properties) {
        this.properties = properties;
    }

    public void addToCart(int customerId, int orderId, int shoeId) {
        String name = properties.getProperty("name");
        String password = properties.getProperty("password");
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shoe_shop_db_new", name, password)) {

            CallableStatement callableStatement = connection.prepareCall("CALL shoe_shop_db_new.addToCart(?,?,?)");
            callableStatement.setInt(1,customerId);
            callableStatement.setInt(2,orderId);
            callableStatement.setInt(3,shoeId);
            callableStatement.execute();

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

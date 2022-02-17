package dbconnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class RepositoryAddToCart {

    public void addToCart(Properties properties) {
        String name = properties.getProperty("name");
        String password = properties.getProperty("password");
        String connectionString = properties.getProperty("connectionString");
        try (Connection connection = DriverManager.getConnection(properties.getProperty("connectionString"), name, password)) {

            CallableStatement callableStatement = connection.prepareCall("CALL shoe_shop_db_new.addToCart(?,?,?)");
            callableStatement.setInt(1,parseInputToInt());
            callableStatement.setInt(2,parseInputToInt());
            callableStatement.setInt(3,parseInputToInt());
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

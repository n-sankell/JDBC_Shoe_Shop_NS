package dbconnection;

import java.sql.*;
import java.util.Scanner;

public class Repository {

    public void viewAverageScores(String name, String password, String connectionString) {
        try (Connection connection = DriverManager.getConnection(connectionString,
                name, password)) {

            CallableStatement callableStatement = connection.prepareCall("CALL shoe_shop_db_new.addToCart(?,?,?)");
            callableStatement.setInt(1,parseInputToInt());
            callableStatement.setInt(2,parseInputToInt());
            callableStatement.setInt(3,parseInputToInt());
            callableStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
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

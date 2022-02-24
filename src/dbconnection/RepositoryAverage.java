package dbconnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class RepositoryAverage {

    private final String name;
    private final String password;

    public RepositoryAverage(Properties properties) {
        this.name = properties.getProperty("name");
        this.password = properties.getProperty("password");
    }

    public List<String> getAverageScoreTable() {
        List<String> averageForAll = new ArrayList<>();
        averageForAll.add("All Average Scores!");
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shoe_shop_db_new", name, password)) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM shoe_shop_db_new.medelbetyg");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String average = resultSet.getString("Label")+"\t "+resultSet.getString("Produkt")+"\tScore: "+resultSet.getDouble("Medelbetyg")+"\t "+resultSet.getString("Rating");
                averageForAll.add(average);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return averageForAll;
    }

    public String getAverageFromProductId(int customerId) {
        String result = "";
        String query = "SELECT shoe_shop_db_new.medelbetyg.Medelbetyg, shoe_shop_db_new.medelbetyg.Rating from shoe_shop_db_new.medelbetyg " +
                "inner join shoe_shop_db_new.base_product on base_product.productName = Medelbetyg.Produkt where shoe_shop_db_new.base_product.id = ?";
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shoe_shop_db_new", name, password);
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1,customerId+"");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                result = "Average score: "+resultSet.getDouble("Medelbetyg")+" "+resultSet.getString("rating");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

}

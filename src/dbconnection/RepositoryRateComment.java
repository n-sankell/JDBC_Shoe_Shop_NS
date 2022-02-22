package dbconnection;

import gui.CustomJop;

import java.sql.*;
import java.util.Properties;

public class RepositoryRateComment {

    private final String name;
    private final String password;

    public RepositoryRateComment(Properties properties) {
        this.name = properties.getProperty("name");
        this.password = properties.getProperty("password");
    }

    public String RateAndComment(int gradeId, String text, int customerId, int shoeId) {
        String message = "";
        Connection connection;
        CallableStatement callableStatement;
        ResultSet resultSet;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shoe_shop_db_new", name, password);
            callableStatement = connection.prepareCall("CALL shoe_shop_db_new.Rate(?,?,?,?)");
            callableStatement.setInt(1,gradeId);
            callableStatement.setString(2,text);
            callableStatement.setInt(3,customerId);
            callableStatement.setInt(4,shoeId);
            callableStatement.execute();

            resultSet = callableStatement.getResultSet();
            while (resultSet.next()) {
                message = resultSet.getString("answer");
            }
            resultSet.close();
            callableStatement.close();
            connection.close();
        } catch (SQLException e) {
            new CustomJop(e.getMessage(), "ok");
        }
        return message;
    }

}

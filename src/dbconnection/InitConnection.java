package dbconnection;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class InitConnection {

    public void startConnection() {
        PropertyReader propertyReader = new PropertyReader();
        propertyReader.readProperties();
        Repository repository = new Repository();
        repository.viewAverageScores(propertyReader.name, propertyReader.password, propertyReader.connectionString);
    }

    private static class PropertyReader {
        private String name;
        private String password;
        private String connectionString;

        private void readProperties() {
            Properties properties = new Properties();
            try {
                properties.load(new FileInputStream("resources/Settings.properties"));
                name = properties.getProperty("name");
                password = properties.getProperty("password");
                connectionString = properties.getProperty("connectionString");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}



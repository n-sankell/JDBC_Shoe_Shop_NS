package controller;

import dbconnection.RepositoryAddToCart;
import dbconnection.RepositoryFindCustomer;
import gui.BaseFrame;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Controller {

    private User user;
    private PropertyReader propertyReader;

    public void startGui() {
        BaseFrame base = new BaseFrame();
        base.startLogin(user);
    }

    public void startConnection() {
        propertyReader = new PropertyReader();
        propertyReader.readProperties();
        getAllCustomers();
    }

    private void addToCart(int customerId, int orderId, int shoeId) {
        RepositoryAddToCart repositoryAddToCart = new RepositoryAddToCart(propertyReader.properties);
        repositoryAddToCart.addToCart(customerId, orderId, shoeId);
    }

    private void getAllCustomers() {
        RepositoryFindCustomer findCustomers = new RepositoryFindCustomer(propertyReader.properties);
        findCustomers.getAllCustomers();
    }

    private static class PropertyReader {
        private Properties properties;

        private void readProperties() {
            properties = new Properties();
            try {
                properties.load(new FileInputStream("resources/Settings.properties"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}


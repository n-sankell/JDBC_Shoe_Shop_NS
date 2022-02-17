package controller;

import dbconnection.RepositoryAddToCart;
import dbconnection.RepositoryFindCustomer;
import gui.BaseFrame;
import gui.CustomJop;
import listeners.LoginListener;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Controller {

    private User user;
    BaseFrame base;
    private PropertyReader propertyReader;
    private LoginListener loginListener;
    private RepositoryFindCustomer findCustomers;

    public Controller() {
        setLoginHandler();
    }

    public void startGui() {
        base = new BaseFrame();
        base.startLogin();
        base.getLoginPanel().setLoginListener(loginListener);
    }

    public void startConnection() {
        propertyReader = new PropertyReader();
        propertyReader.readProperties();
    }

    private void addToCart(int customerId, int orderId, int shoeId) {
        RepositoryAddToCart repositoryAddToCart = new RepositoryAddToCart(propertyReader.properties);
        repositoryAddToCart.addToCart(customerId, orderId, shoeId);
    }

    private void getCustomersFromServer() {
        findCustomers = new RepositoryFindCustomer(propertyReader.properties);
        findCustomers.fetchCustomersToList();
    }

    private void setLoginHandler() {
        loginListener = (username, password) -> {
            getCustomersFromServer();
            findCustomers.getCustomers().stream()
                    .filter(customer -> customer.getName().equals(username) && customer.getPassword().equals(password))
                    .toList().forEach(customer -> user = new User(customer.getId(), customer.getName()));
            String message = user == null ? "Username and password do not match! " : "Welcome "+user.name()+"!";
            String buttonText = user == null ? "Try again" : "Let's shop!";
            new CustomJop(message, buttonText);
            //JOptionPane.showMessageDialog(null,message);
            if (user != null) {
                base.removeLogin();
                base.addShopPanel();
            }
        };
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


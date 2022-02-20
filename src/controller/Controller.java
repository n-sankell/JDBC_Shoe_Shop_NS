package controller;

import dbconnection.RepositoryAddToCart;
import dbconnection.RepositoryFIllObjects;
import dbconnection.RepositoryFindCustomer;
import dbobjectmodel.BaseProduct;
import gui.BaseFrame;
import gui.CustomJop;
import listeners.LogOutListener;
import listeners.LoginListener;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class Controller {

    private User user;
    private BaseFrame base;
    private PropertyReader propertyReader;
    private LoginListener loginListener;
    private LogOutListener logOutListener;
    private List<BaseProduct> productList;
    private RepositoryFindCustomer findCustomers;

    public Controller() {
        setEventHandler();
    }

    public void startGui() {
        base = new BaseFrame();
        base.startLogin();
        base.getLoginPanel().setLoginListener(loginListener);
        base.getShopPanel().setLogOutListener(logOutListener);
    }

    public void startConnection() {
        propertyReader = new PropertyReader();
        propertyReader.readProperties();
        populateShoeList();
    }

    private void populateShoeList() {
        RepositoryFIllObjects repo = new RepositoryFIllObjects(propertyReader.properties);
        productList = repo.getBaseProducts();
    }

    private void addToCart(int customerId, int orderId, int shoeId) {
        RepositoryAddToCart repositoryAddToCart = new RepositoryAddToCart(propertyReader.properties);
        repositoryAddToCart.addToCart(customerId, orderId, shoeId);
    }

    private void loadCustomersFromServer() {
        findCustomers = new RepositoryFindCustomer(propertyReader.properties);
        findCustomers.fetchCustomersToList();
    }

    private void setEventHandler() {
        loginListener = (username, password) -> {
            loadCustomersFromServer();
            findCustomers.getCustomers().stream()
                    .filter(customer -> customer.getName().equals(username) && customer.getPassword().equals(password))
                    .toList().forEach(customer -> user = new User(customer.getId(), customer.getName()));
            String message = user == null ? "Username and password do not match! " : "Welcome "+user.getName()+"!";
            String buttonText = user == null ? "Try again" : "Let's shop!";
            new CustomJop(message, buttonText);
            if (user != null) {
                base.removeLogin();
                base.addShopPanel();
                base.getShopPanel().getScrollablePanel().fillMap(productList);
            }
        };
        logOutListener = () -> {
            user = null;
            base.removeShopPanel();
            base.startLogin();
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


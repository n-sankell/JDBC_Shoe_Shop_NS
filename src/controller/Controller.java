package controller;

import dbconnection.RepositoryAddToCart;
import dbconnection.RepositoryFIllObjects;
import dbconnection.RepositoryFindCustomer;
import dbobjectmodel.BaseProduct;
import gui.BaseFrame;
import gui.CustomJop;
import listeners.*;

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
    private ShoeDetailsListener shoeDetailsListener;
    private AddToCartListener addToCartListener;
    private GoBackListener goBackListener;
    private List<BaseProduct> productList;
    private RepositoryFindCustomer findCustomers;

    public Controller() {
        setEventHandler();
    }

    public void startGui() {
        base = new BaseFrame();
        base.startLogin();
        base.getLoginPanel().setLoginListener(loginListener);
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
        repositoryAddToCart.addToNewCart(customerId, orderId, shoeId);
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
                base.setUpShopPanel();
                setUpListeners();
                base.addShopPanel();
                base.getShopPanel().addScrollPane();
                base.getShopPanel().getScrollablePanel().fillMap(productList);
            }
        };
        logOutListener = () -> {
            user = null;
            base.getShopPanel().getShoeDetails().removeDetails();
            base.getShopPanel().removeDetails();
            base.getShopPanel().addScrollPane();
            base.removeShopPanel();
            base.startLogin();
        };
        shoeDetailsListener = product -> {
            base.getShopPanel().getShoeDetails().setProduct(product);
            base.getShopPanel().getShoeDetails().addDetails();
            base.getShopPanel().removeScrollPane();
            base.getShopPanel().addDetails();
        };
        goBackListener = () -> {
            base.getShopPanel().removeDetails();
            base.getShopPanel().createDetailPanel();
            setUpListeners();
            base.getShopPanel().addScrollPane();
        };
        addToCartListener = (shoe) -> {
            String message = "";
            RepositoryAddToCart repositoryAddToCart = new RepositoryAddToCart(propertyReader.properties);
            if (user.getShoes().isEmpty()) {
                message = repositoryAddToCart.addToNewCart(user.getId(),0,shoe.getId());
            } else {
                message = repositoryAddToCart.addToExistingCart(user.getId(),shoe.getId());
            }
            if (!message.equals("")) {
                user.addShoe(shoe);
                new CustomJop(message,"ok");
                shoe.setAmountInStock(shoe.getAmountInStock()-1);
                base.getShopPanel().getShoeDetails().updateInStock();
            }
        };
    }

    private void setUpListeners() {
        base.getShopPanel().setLogOutListener(logOutListener);
        base.getShopPanel().getScrollablePanel().setShoeDetailsListener(shoeDetailsListener);
        base.getShopPanel().getShoeDetails().setGoBackListener(goBackListener);
        base.getShopPanel().getShoeDetails().setAddToCartListener(addToCartListener);
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


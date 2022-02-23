package controller;

import dbconnection.*;
import dbobjectmodel.BaseProduct;
import dbobjectmodel.Customer;
import gui.*;
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
    private AllAveragesListener allAveragesListener;
    private ShoeDetailsListener shoeDetailsListener;
    private AddToCartListener addToCartListener;
    private RateCommentListener rateCommentListener;
    private RateAndCommentSubmitListener submitListener;
    private GoBackListener goBackListener;
    private CheckoutListener checkoutListener;
    private ViewCartListener viewCartListener;
    private RepositoryAverage averageRepo;
    private List<Customer> customers;
    private List<BaseProduct> productList;
    private List<String> allAverages;
    private ViewCartFrame viewCartFrame;
    private AllAveragesFrame allAveragesFrame;
    private RateAndCommentFrame rateAndCommentFrame;

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
        populateAverageList();
    }

    private void populateAverageList() {
        averageRepo = new RepositoryAverage(propertyReader.properties);
        allAverages = averageRepo.getAverageScoreTable();
    }

    private String getAverageByProductId(int id) {
        return averageRepo.getAverageFromProductId(id);
    }

    private String rateAndCommentProduct(int gradeId, String text, int customerId, int shoeId) {
        RepositoryRateComment rateComment = new RepositoryRateComment(propertyReader.properties);
        return rateComment.RateAndComment(gradeId,text,customerId,shoeId);
    }

    private void populateShoeList() {
        RepositoryFIllObjects repo = new RepositoryFIllObjects(propertyReader.properties);
        productList = repo.getBaseProducts();
    }

    private void populateCustomerList() {
        RepositoryFindCustomer findCustomers = new RepositoryFindCustomer(propertyReader.properties);
        findCustomers.fetchCustomersToList();
        customers = findCustomers.getCustomers();
    }

    private void linkInstances() {
        productList.forEach(shoe -> shoe.getRatings().forEach(rating -> rating.setCustomer(customers.stream()
                        .filter(customer -> customer.getId() == rating.getCustomerId()).toList().get(0))));
        productList.forEach(shoe -> shoe.getComments().forEach(comment -> comment.setCustomer(customers.stream()
                        .filter(customer -> customer.getId() == comment.getCustomerId()).toList().get(0))));

        productList.forEach(product -> product.getCategories().forEach(category -> category.addBaseProduct(product)));
        productList.forEach(product -> product.getComments().forEach(comment -> comment.getCustomer().addComment(comment)));
        productList.forEach(product -> product.getRatings().forEach(rating -> rating.getCustomer().addRating(rating)));
        productList.forEach(product -> product.getLabel().addProduct(product));
        productList.forEach(product -> product.getShoes().forEach(shoe -> shoe.getOrders().forEach(order -> order.addShoe(shoe))));

        productList.forEach(product -> product.getShoes().forEach(shoe -> shoe.getOrders().forEach(order -> order.setCustomer(customers.stream()
                .filter(customer -> customer.getId() == order.getCustomerId()).toList().get(0)))));

    }

    public void printOutProductsFromSpecificCategory(String filterCategory) {
        productList.forEach(product -> product.getCategories().stream().filter(category -> category
                .getName().equals(filterCategory)).forEach(category -> category.getBaseProducts()
                .forEach(prod -> System.out.println(category.getName()+" "+prod.getName()))));
    }

    private void setEventHandler() {
        loginListener = (username, password) -> {
            populateCustomerList();
            customers.stream().filter(customer -> customer.getName().equals(username) && customer.getPassword().equals(password))
                    .forEach(customer -> user = new User(customer.getId(), customer.getName(), customer));

            String message = user == null ? "Username and password do not match! " : "Welcome "+user.getName()+"!";
            String buttonText = user == null ? "Try again" : "Let's shop!";

            new CustomJop(message, buttonText);

            if (user != null) {
                linkInstances();
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
            base.getShopPanel().getShoeDetails().setAverageScore(getAverageByProductId(product.getId()));
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
            String message;
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
        checkoutListener = () -> {
            new CustomJop("Thank you for your purchase!","ok");
            user.clearShoes();
            base.getShopPanel().getShoeDetails().resetCounters();
            updateAll();
            viewCartFrame.dispose();
        };
        viewCartListener = () -> {
            if (user.getShoes().isEmpty()) {
                new CustomJop("Your cart is empty!","ok");
            } else {
                viewCartFrame = new ViewCartFrame(user.getShoes());
                viewCartFrame.setCheckoutListener(checkoutListener);
                viewCartFrame.setVisible(true);
            }
        };
        allAveragesListener = () -> {
          allAveragesFrame = new AllAveragesFrame(allAverages);
          allAveragesFrame.setVisible(true);
        };
        rateCommentListener = (product, average, ratings, comments) -> {
            rateAndCommentFrame = new RateAndCommentFrame(product, average, ratings, comments, user.getId());
            rateAndCommentFrame.setSubmitListener(submitListener);
        };
        submitListener = (gradeId, text, customerId, shoeId) -> {
            new CustomJop(rateAndCommentProduct(gradeId,text,customerId,shoeId),"No problem!");
            updateAll();
            rateAndCommentFrame.dispose();
        };

    }

    private void setUpListeners() {
        base.getShopPanel().setAllAveragesListener(allAveragesListener);
        base.getShopPanel().setLogOutListener(logOutListener);
        base.getShopPanel().getScrollablePanel().setShoeDetailsListener(shoeDetailsListener);
        base.getShopPanel().getShoeDetails().setGoBackListener(goBackListener);
        base.getShopPanel().getShoeDetails().setAddToCartListener(addToCartListener);
        base.getShopPanel().getShoeDetails().setViewCartListener(viewCartListener);
        base.getShopPanel().getShoeDetails().setRateCommentListener(rateCommentListener);
    }

    private void updateAll() {
        populateShoeList();
        populateAverageList();
        populateCustomerList();
        base.getShopPanel().getShoeDetails().removeDetails();
        base.getShopPanel().removeDetails();
        base.getShopPanel().addScrollPane();
        base.removeShopPanel();
        linkInstances();
        base.setUpShopPanel();
        setUpListeners();
        base.addShopPanel();
        base.getShopPanel().addScrollPane();
        base.getShopPanel().getScrollablePanel().fillMap(productList);
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


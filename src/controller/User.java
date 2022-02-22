package controller;

import dbobjectmodel.CompleteShoe;
import dbobjectmodel.Customer;

import java.util.ArrayList;
import java.util.List;

public class User {

    private final int id;
    private final String name;
    private final Customer customer;
    private final List<CompleteShoe> shoes = new ArrayList<>();

    public User(int id, String name, Customer customer) {
        this.customer = customer;
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<CompleteShoe> getShoes() {
        return shoes;
    }

    public void addShoe(CompleteShoe shoe) {
        shoes.add(shoe);
    }

    public void clearShoes() {
        shoes.clear();
    }

    public Customer getCustomer() {
        return customer;
    }


}

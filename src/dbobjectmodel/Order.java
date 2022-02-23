package dbobjectmodel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {

    private final int id;
    private final int customerId;
    private final Date date;
    private Customer customer;
    private final List<CompleteShoe> shoes = new ArrayList<>();

    public Order(int id, int customerId, Date date) {
        this.id = id;
        this.customerId = customerId;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public Date getDate() {
        return date;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<CompleteShoe> getShoes() {
        return shoes;
    }

    public void addShoe(CompleteShoe shoe) {
        shoes.add(shoe);
    }
}

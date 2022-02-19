package dbobjectmodel;

import java.util.ArrayList;
import java.util.List;

public class Area {

    private final int id;
    private final String name;
    private final List<Customer> customers = new ArrayList<>();

    public Area(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public List<Customer> getCustomers() {
        return customers;
    }
}

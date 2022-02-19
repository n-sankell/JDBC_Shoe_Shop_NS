package dbobjectmodel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private final int id;
    private final int customerId;
    private final LocalDateTime date;
    private final List<CompleteShoe> shoes = new ArrayList<>();

    public Order(int id, int customerId, LocalDateTime date) {
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

    public LocalDateTime getDate() {
        return date;
    }

    public List<CompleteShoe> getShoes() {
        return shoes;
    }

    public void addShoe(CompleteShoe shoe) {
        shoes.add(shoe);
    }
}

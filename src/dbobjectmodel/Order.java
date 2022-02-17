package dbobjectmodel;

import java.time.LocalDateTime;

public class Order {

    private final int id;
    private final int customerId;
    private final LocalDateTime date;

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
}

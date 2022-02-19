package dbobjectmodel;

import java.time.LocalDateTime;

public class Comment {

    private final int id;
    private final String text;
    private final int customerId;
    private final int productId;
    private final LocalDateTime date;
    private BaseProduct baseProduct;
    private Customer customer;

    public Comment(int id, String text, int customerId, int productId, LocalDateTime date) {
        this.id = id;
        this.text = text;
        this.customerId = customerId;
        this.productId = productId;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getProductId() {
        return productId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public BaseProduct getBaseProduct() {
        return baseProduct;
    }

    public void setBaseProduct(BaseProduct baseProduct) {
        this.baseProduct = baseProduct;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}

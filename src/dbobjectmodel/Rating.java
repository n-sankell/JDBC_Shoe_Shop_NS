package dbobjectmodel;

import java.sql.Date;

public class Rating {

    private final int id;
    private final int gradeId;
    private final int customerId;
    private final int productId;
    private final Date date;
    private RatingGrade grade;
    private Customer customer;
    private BaseProduct product;

    public Rating(int id, int getId, int customerId, int productId, Date date) {
        this.id = id;
        this.gradeId = getId;
        this.customerId = customerId;
        this.productId = productId;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public int getGradeId() {
        return gradeId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getProductId() {
        return productId;
    }

    public Date getDate() {
        return date;
    }

    public RatingGrade getGrade() {
        return grade;
    }

    public void setGrade(RatingGrade grade) {
        this.grade = grade;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public BaseProduct getProduct() {
        return product;
    }

    public void setProduct(BaseProduct product) {
        this.product = product;
    }
}

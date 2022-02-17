package dbobjectmodel;

import java.time.LocalDateTime;

public class Rating {

    private final int id;
    private final int gradeId;
    private final int customerId;
    private final int productId;
    private final LocalDateTime date;

    public Rating(int id, int getId, int customerId, int productId, LocalDateTime date) {
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

    public LocalDateTime getDate() {
        return date;
    }
}

package dbobjectmodel;

public class CompleteShoe {

    private final int id;
    private final int baseProductId;
    private final int sizeId;
    private final int priceId;
    private int amountInStock;

    public CompleteShoe(int id, int baseProductId, int sizeId, int priceId, int amountInStock) {
        this.id = id;
        this.baseProductId = baseProductId;
        this.sizeId = sizeId;
        this.priceId = priceId;
        this.amountInStock = amountInStock;
    }

    public int getId() {
        return id;
    }

    public int getBaseProductId() {
        return baseProductId;
    }

    public int getSizeId() {
        return sizeId;
    }

    public int getPriceId() {
        return priceId;
    }

    public int getAmountInStock() {
        return amountInStock;
    }

    public void setAmountInStock(int amountInStock) {
        this.amountInStock = amountInStock;
    }
}

package dbobjectmodel;

import java.util.ArrayList;
import java.util.List;

public class CompleteShoe {

    private final int id;
    private final int baseProductId;
    private final int sizeId;
    private final int priceId;
    private int amountInStock;
    private BaseProduct product;
    private Size size;
    private Price price;

    private final List<OutOfStock> outOfStockList = new ArrayList<>();
    private final List<Order> orders = new ArrayList<>();
    private final List<ShoeColor> colors = new ArrayList<>();

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

    public BaseProduct getProduct() {
        return product;
    }

    public void setProduct(BaseProduct product) {
        this.product = product;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public List<ShoeColor> getColors() {
        return colors;
    }

    public void addColor(ShoeColor color) {
        colors.add(color);
    }

    public List<OutOfStock> getOutOfStockList() {
        return outOfStockList;
    }

    public void addOutOfStock(OutOfStock outOfStock) {
        outOfStockList.add(outOfStock);
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

}

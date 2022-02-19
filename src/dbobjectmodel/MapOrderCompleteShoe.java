package dbobjectmodel;

public class MapOrderCompleteShoe {

    private final int id;
    private final int orderId;
    private final int shoeId;
    private Order order;
    private CompleteShoe shoe;

    public MapOrderCompleteShoe(int id, int orderId, int shoeId) {
        this.id = id;
        this.orderId = orderId;
        this.shoeId = shoeId;
    }

    public int getId() {
        return id;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getShoeId() {
        return shoeId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public CompleteShoe getShoe() {
        return shoe;
    }

    public void setShoe(CompleteShoe shoe) {
        this.shoe = shoe;
    }
}

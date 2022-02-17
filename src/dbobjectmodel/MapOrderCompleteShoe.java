package dbobjectmodel;

public class MapOrderCompleteShoe {

    private final int id;
    private final int orderId;
    private final int shoeId;

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
}

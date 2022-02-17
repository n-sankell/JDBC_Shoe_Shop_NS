package dbobjectmodel;

public class Price {

    private final int id;
    private final double price;

    public Price(int id, double price) {
        this.id = id;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }
}

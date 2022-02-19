package dbobjectmodel;

import java.util.ArrayList;
import java.util.List;

public class Price {

    private final int id;
    private final double price;
    private final List<CompleteShoe> shoes = new ArrayList<>();

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

    public List<CompleteShoe> getShoes() {
        return shoes;
    }

    public void addShoe(CompleteShoe shoe) {
        shoes.add(shoe);
    }
}

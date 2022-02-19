package dbobjectmodel;

import java.util.ArrayList;
import java.util.List;

public class Label {

    private final int id;
    private final String name;
    private final List<BaseProduct> products = new ArrayList<>();

    public Label(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<BaseProduct> getProducts() {
        return products;
    }

    public void addProduct(BaseProduct product) {
        products.add(product);
    }
}

package dbobjectmodel;

import java.util.ArrayList;
import java.util.List;

public class Category {

    private final int id;
    private final String name;
    private final List<BaseProduct> baseProducts = new ArrayList<>();

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void addBaseProduct(BaseProduct baseProduct) {
        baseProducts.add(baseProduct);
    }

    public List<BaseProduct> getBaseProducts() {
        return baseProducts;
    }
}

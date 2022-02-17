package dbobjectmodel;

public class MapProductCategory {

    private final int id;
    private final int productId;
    private final int categoryId;

    public MapProductCategory(int id, int productId, int categoryId) {
        this.id = id;
        this.productId = productId;
        this.categoryId = categoryId;
    }

    public int getId() {
        return id;
    }

    public int getProductId() {
        return productId;
    }

    public int getCategoryId() {
        return categoryId;
    }
}

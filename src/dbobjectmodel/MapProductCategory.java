package dbobjectmodel;

public class MapProductCategory {

    private final int id;
    private final int productId;
    private final int categoryId;
    private BaseProduct product;
    private Category category;

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

    public BaseProduct getProduct() {
        return product;
    }

    public void setProduct(BaseProduct product) {
        this.product = product;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}

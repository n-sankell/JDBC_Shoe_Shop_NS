package dbobjectmodel;

import java.util.ArrayList;
import java.util.List;

public class BaseProduct {

    private final int id;
    private final int labelId;
    private final String name;
    private Label label;
    private final List<Category> categories = new ArrayList<>();

    public BaseProduct(int id, int labelId, String name) {
        this.id = id;
        this.labelId = labelId;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getLabelId() {
        return labelId;
    }

    public String getName() {
        return name;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public void addCategory(Category category) {
        categories.add(category);
    }

    public List<Category> getCategories() {
        return categories;
    }
}

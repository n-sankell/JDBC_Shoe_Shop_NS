package dbobjectmodel;

public class BaseProduct {

    private final int id;
    private final int labelId;
    private final String name;

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
}

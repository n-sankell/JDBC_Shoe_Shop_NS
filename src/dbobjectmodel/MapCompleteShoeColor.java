package dbobjectmodel;

public class MapCompleteShoeColor {

    private final int id;
    private final int shoeId;
    private final int colorId;

    public MapCompleteShoeColor(int id, int shoeId, int colorId) {
        this.id = id;
        this.shoeId = shoeId;
        this.colorId = colorId;
    }

    public int getId() {
        return id;
    }

    public int getShoeId() {
        return shoeId;
    }

    public int getColorId() {
        return colorId;
    }
}

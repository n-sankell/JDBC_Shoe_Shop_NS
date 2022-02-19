package dbobjectmodel;

public class MapCompleteShoeColor {

    private final int id;
    private final int shoeId;
    private final int colorId;
    private CompleteShoe shoe;
    private ShoeColor color;

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

    public CompleteShoe getShoe() {
        return shoe;
    }

    public void setShoe(CompleteShoe shoe) {
        this.shoe = shoe;
    }

    public ShoeColor getColor() {
        return color;
    }

    public void setColor(ShoeColor color) {
        this.color = color;
    }
}

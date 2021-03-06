package dbobjectmodel;

import java.util.ArrayList;
import java.util.List;

public class Size {

    private final int id;
    private final double size;
    private final List<CompleteShoe> shoes = new ArrayList<>();

    public Size(int id, double size) {
        this.id = id;
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public double getSize() {
        return size;
    }

    public void addShoe(CompleteShoe shoe) {
        shoes.add(shoe);
    }

    public List<CompleteShoe> getShoes() {
        return shoes;
    }
}
